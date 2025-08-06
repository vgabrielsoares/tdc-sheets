# Utils - Funções Utilitárias

## Visão Geral
Conjunto de funções utilitárias reutilizáveis para operações comuns, formatação, validação e manipulação de dados na aplicação TDC Sheets.

## Categorias de Utilitários

### Formatação e Display
- **formatters.ts** - Formatação de dados para exibição
- **currency.ts** - Formatação de moedas do jogo
- **date.ts** - Formatação e manipulação de datas
- **text.ts** - Manipulação de strings e texto

### Validação
- **validators.ts** - Validadores de formulários
- **rules.ts** - Validações específicas do RPG
- **sanitizers.ts** - Sanitização de inputs

### Manipulação de Dados
- **arrays.ts** - Operações com arrays
- **objects.ts** - Manipulação de objetos
- **math.ts** - Cálculos matemáticos
- **gameRules.ts** - Cálculos específicos do jogo

### Browser e DOM
- **storage.ts** - LocalStorage e SessionStorage
- **clipboard.ts** - Operações de clipboard
- **file.ts** - Manipulação de arquivos
- **url.ts** - Manipulação de URLs

### Async e Performance
- **debounce.ts** - Debounce e throttle
- **cache.ts** - Sistema de cache simples
- **retry.ts** - Retry automático para operações

## Formatadores

### Formatação de Texto
```typescript
// formatters.ts
export const formatters = {
  /**
   * Capitaliza a primeira letra de cada palavra
   */
  titleCase: (str: string): string => {
    return str.replace(/\w\S*/g, (txt) => 
      txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase()
    );
  },

  /**
   * Remove acentos de strings
   */
  removeAccents: (str: string): string => {
    return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  },

  /**
   * Cria slug a partir de string
   */
  slugify: (str: string): string => {
    return formatters.removeAccents(str)
      .toLowerCase()
      .replace(/[^a-z0-9 -]/g, '')
      .replace(/\s+/g, '-')
      .replace(/-+/g, '-')
      .trim();
  },

  /**
   * Trunca texto com reticências
   */
  truncate: (str: string, length: number): string => {
    if (str.length <= length) return str;
    return str.slice(0, length).trim() + '...';
  },

  /**
   * Pluraliza palavras simples
   */
  pluralize: (word: string, count: number): string => {
    if (count === 1) return word;
    
    const pluralRules: Record<string, string> = {
      'ficha': 'fichas',
      'personagem': 'personagens',
      'atributo': 'atributos',
      'habilidade': 'habilidades',
      'feitiço': 'feitiços',
      'equipamento': 'equipamentos'
    };

    return pluralRules[word] || `${word}s`;
  }
};
```

### Formatação de Datas
```typescript
// date.ts
export const dateUtils = {
  /**
   * Formata data para exibição brasileira
   */
  formatBR: (date: Date | string): string => {
    const d = new Date(date);
    return d.toLocaleDateString('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
    });
  },

  /**
   * Formata data e hora completa
   */
  formatDateTime: (date: Date | string): string => {
    const d = new Date(date);
    return d.toLocaleString('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  },

  /**
   * Retorna tempo relativo (ex: "2 horas atrás")
   */
  timeAgo: (date: Date | string): string => {
    const now = new Date();
    const past = new Date(date);
    const diffMs = now.getTime() - past.getTime();
    
    const minute = 60 * 1000;
    const hour = minute * 60;
    const day = hour * 24;
    const week = day * 7;
    const month = day * 30;
    const year = day * 365;

    if (diffMs < minute) return 'agora mesmo';
    if (diffMs < hour) return `${Math.floor(diffMs / minute)}m atrás`;
    if (diffMs < day) return `${Math.floor(diffMs / hour)}h atrás`;
    if (diffMs < week) return `${Math.floor(diffMs / day)}d atrás`;
    if (diffMs < month) return `${Math.floor(diffMs / week)}sem atrás`;
    if (diffMs < year) return `${Math.floor(diffMs / month)}mês atrás`;
    return `${Math.floor(diffMs / year)}ano atrás`;
  },

  /**
   * Verifica se data é hoje
   */
  isToday: (date: Date | string): boolean => {
    const today = new Date();
    const d = new Date(date);
    return today.toDateString() === d.toDateString();
  },

  /**
   * Adiciona dias a uma data
   */
  addDays: (date: Date, days: number): Date => {
    const result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }
};
```

### Formatação de Moedas do Jogo
```typescript
// currency.ts
export interface CunhagemPersonagem {
  po: number; // Peças de ouro
  pp: number; // Peças de prata
  pc: number; // Peças de cobre
}

export const currencyUtils = {
  /**
   * Formata moedas para exibição
   */
  format: (cunhagem: Partial<CunhagemPersonagem>): string => {
    const parts: string[] = [];
    
    if (cunhagem.po && cunhagem.po > 0) {
      parts.push(`${cunhagem.po} PO`);
    }
    if (cunhagem.pp && cunhagem.pp > 0) {
      parts.push(`${cunhagem.pp} PP`);
    }
    if (cunhagem.pc && cunhagem.pc > 0) {
      parts.push(`${cunhagem.pc} PC`);
    }

    return parts.length > 0 ? parts.join(', ') : '0 PC';
  },

  /**
   * Converte tudo para peças de cobre
   */
  toCopperPieces: (cunhagem: Partial<CunhagemPersonagem>): number => {
    const po = (cunhagem.po || 0) * 100;
    const pp = (cunhagem.pp || 0) * 10;
    const pc = cunhagem.pc || 0;
    return po + pp + pc;
  },

  /**
   * Converte peças de cobre para cunhagem otimizada
   */
  fromCopperPieces: (totalCobre: number): CunhagemPersonagem => {
    const po = Math.floor(totalCobre / 100);
    const remaining = totalCobre % 100;
    const pp = Math.floor(remaining / 10);
    const pc = remaining % 10;

    return { po, pp, pc };
  },

  /**
   * Soma cunhagens
   */
  add: (
    c1: Partial<CunhagemPersonagem>,
    c2: Partial<CunhagemPersonagem>
  ): CunhagemPersonagem => {
    const total = currencyUtils.toCopperPieces(c1) + currencyUtils.toCopperPieces(c2);
    return currencyUtils.fromCopperPieces(total);
  },

  /**
   * Subtrai cunhagens
   */
  subtract: (
    c1: Partial<CunhagemPersonagem>,
    c2: Partial<CunhagemPersonagem>
  ): CunhagemPersonagem => {
    const total = Math.max(0, currencyUtils.toCopperPieces(c1) - currencyUtils.toCopperPieces(c2));
    return currencyUtils.fromCopperPieces(total);
  }
};
```

## Validadores

### Validações de Formulário
```typescript
// validators.ts
export const validators = {
  /**
   * Validação de email
   */
  email: (value: string): string | undefined => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
      return 'Email inválido';
    }
  },

  /**
   * Validação de senha forte
   */
  password: (value: string): string | undefined => {
    if (value.length < 8) {
      return 'Senha deve ter pelo menos 8 caracteres';
    }
    if (!/(?=.*[a-z])/.test(value)) {
      return 'Senha deve conter pelo menos uma letra minúscula';
    }
    if (!/(?=.*[A-Z])/.test(value)) {
      return 'Senha deve conter pelo menos uma letra maiúscula';
    }
    if (!/(?=.*\d)/.test(value)) {
      return 'Senha deve conter pelo menos um número';
    }
  },

  /**
   * Validação de confirmação de senha
   */
  confirmPassword: (password: string, confirmPassword: string): string | undefined => {
    if (password !== confirmPassword) {
      return 'Senhas não coincidem';
    }
  },

  /**
   * Validação de comprimento
   */
  length: (min: number, max?: number) => (value: string): string | undefined => {
    if (value.length < min) {
      return `Mínimo ${min} caracteres`;
    }
    if (max && value.length > max) {
      return `Máximo ${max} caracteres`;
    }
  },

  /**
   * Validação de campo obrigatório
   */
  required: (value: any): string | undefined => {
    if (value === null || value === undefined || value === '') {
      return 'Este campo é obrigatório';
    }
  },

  /**
   * Validação de número
   */
  number: (min?: number, max?: number) => (value: number): string | undefined => {
    if (isNaN(value)) {
      return 'Deve ser um número válido';
    }
    if (min !== undefined && value < min) {
      return `Valor mínimo: ${min}`;
    }
    if (max !== undefined && value > max) {
      return `Valor máximo: ${max}`;
    }
  }
};
```

### Validações Específicas do RPG
```typescript
// rules.ts
import type { AtributoPersonagem, TipoAtributo } from '../types';

export const gameValidators = {
  /**
   * Valida se atributo está dentro dos limites
   */
  atributo: (valor: number): string | undefined => {
    if (valor < 1) return 'Atributo não pode ser menor que 1';
    if (valor > 20) return 'Atributo não pode ser maior que 20';
  },

  /**
   * Valida distribuição total de pontos de atributo
   */
  pontosTotais: (atributos: AtributoPersonagem[], limite: number = 72): string | undefined => {
    const total = atributos.reduce((sum, attr) => sum + attr.valor, 0);
    if (total > limite) {
      return `Total de pontos excede o limite (${total}/${limite})`;
    }
  },

  /**
   * Valida nível de personagem
   */
  nivel: (nivel: number): string | undefined => {
    if (nivel < 1) return 'Nível mínimo é 1';
    if (nivel > 20) return 'Nível máximo é 20';
  },

  /**
   * Valida círculo de feitiço
   */
  circuloFeitico: (circulo: number, nivelConjurador: number): string | undefined => {
    const circuloMaximo = Math.ceil(nivelConjurador / 2);
    if (circulo > circuloMaximo) {
      return `Círculo máximo para nível ${nivelConjurador} é ${circuloMaximo}`;
    }
  },

  /**
   * Valida capacidade de carga
   */
  capacidadeCarga: (cargaAtual: number, capacidadeMaxima: number): string | undefined => {
    if (cargaAtual > capacidadeMaxima) {
      return `Carga excede capacidade máxima (${cargaAtual}/${capacidadeMaxima} kg)`;
    }
  }
};
```

## Cálculos do Jogo

### Cálculos de Atributos
```typescript
// gameRules.ts
export const gameCalculations = {
  /**
   * Calcula modificador de atributo
   */
  modificadorAtributo: (valor: number): number => {
    return Math.floor((valor - 10) / 2);
  },

  /**
   * Calcula PV baseado em constituição e arquétipo
   */
  calcularPV: (
    constituicao: number,
    arquetipoData: { pvInicial: number; pvNivel: number },
    nivel: number = 1
  ): number => {
    const modConstituicao = gameCalculations.modificadorAtributo(constituicao);
    const pvBase = arquetipoData.pvInicial + modConstituicao;
    const pvNiveis = arquetipoData.pvNivel * (nivel - 1);
    return Math.max(1, pvBase + pvNiveis);
  },

  /**
   * Calcula PP baseado em presença e arquétipo
   */
  calcularPP: (
    presenca: number,
    arquetipoData: { ppInicial: number; ppNivel: number },
    nivel: number = 1
  ): number => {
    const modPresenca = gameCalculations.modificadorAtributo(presenca);
    const ppBase = arquetipoData.ppInicial + modPresenca;
    const ppNiveis = arquetipoData.ppNivel * (nivel - 1);
    return Math.max(0, ppBase + ppNiveis);
  },

  /**
   * Calcula defesa baseada em agilidade e equipamentos
   */
  calcularDefesa: (
    agilidade: number,
    armadura: number = 0,
    limiteAgilidade?: number
  ): number => {
    let modAgilidade = gameCalculations.modificadorAtributo(agilidade);
    
    if (limiteAgilidade !== undefined) {
      modAgilidade = Math.min(modAgilidade, limiteAgilidade);
    }
    
    return 10 + modAgilidade + armadura;
  },

  /**
   * Calcula capacidade de carga
   */
  calcularCapacidadeCarga: (forca: number, tamanho: string): number => {
    const baseCarga = forca * 5; // kg
    
    const multiplicadores: Record<string, number> = {
      'MINUSCULO': 0.25,
      'PEQUENO': 0.5,
      'MEDIO': 1,
      'GRANDE': 2,
      'ENORME': 4,
      'COLOSSAL': 8
    };
    
    return baseCarga * (multiplicadores[tamanho] || 1);
  },

  /**
   * Calcula bônus de proficiência por nível
   */
  bonusProficiencia: (nivel: number): number => {
    return Math.ceil(nivel / 4) + 1;
  },

  /**
   * Calcula teste de atributo
   */
  testeAtributo: (
    valorAtributo: number,
    bonusProficiencia: number = 0,
    outros: number = 0
  ): number => {
    const d20 = Math.floor(Math.random() * 20) + 1;
    const modificador = gameCalculations.modificadorAtributo(valorAtributo);
    return d20 + modificador + bonusProficiencia + outros;
  }
};
```

## Utilitários de Array e Objeto

### Manipulação de Arrays
```typescript
// arrays.ts
export const arrayUtils = {
  /**
   * Remove item por índice
   */
  removeAt: <T>(array: T[], index: number): T[] => {
    return array.filter((_, i) => i !== index);
  },

  /**
   * Move item de uma posição para outra
   */
  move: <T>(array: T[], from: number, to: number): T[] => {
    const result = [...array];
    const [removed] = result.splice(from, 1);
    result.splice(to, 0, removed);
    return result;
  },

  /**
   * Agrupa array por propriedade
   */
  groupBy: <T, K extends keyof T>(array: T[], key: K): Record<string, T[]> => {
    return array.reduce((groups, item) => {
      const groupKey = String(item[key]);
      groups[groupKey] = groups[groupKey] || [];
      groups[groupKey].push(item);
      return groups;
    }, {} as Record<string, T[]>);
  },

  /**
   * Remove duplicatas por propriedade
   */
  uniqueBy: <T, K extends keyof T>(array: T[], key: K): T[] => {
    const seen = new Set();
    return array.filter(item => {
      const value = item[key];
      if (seen.has(value)) return false;
      seen.add(value);
      return true;
    });
  },

  /**
   * Soma valores de propriedade numérica
   */
  sumBy: <T>(array: T[], key: keyof T): number => {
    return array.reduce((sum, item) => {
      const value = item[key];
      return sum + (typeof value === 'number' ? value : 0);
    }, 0);
  },

  /**
   * Embaralha array
   */
  shuffle: <T>(array: T[]): T[] => {
    const result = [...array];
    for (let i = result.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [result[i], result[j]] = [result[j], result[i]];
    }
    return result;
  }
};
```

### Manipulação de Objetos
```typescript
// objects.ts
export const objectUtils = {
  /**
   * Deep clone de objeto
   */
  deepClone: <T>(obj: T): T => {
    if (obj === null || typeof obj !== 'object') return obj;
    if (obj instanceof Date) return new Date(obj.getTime()) as unknown as T;
    if (obj instanceof Array) return obj.map(item => objectUtils.deepClone(item)) as unknown as T;
    
    const cloned = {} as T;
    for (const key in obj) {
      if (obj.hasOwnProperty(key)) {
        cloned[key] = objectUtils.deepClone(obj[key]);
      }
    }
    return cloned;
  },

  /**
   * Merge profundo de objetos
   */
  deepMerge: <T extends Record<string, any>>(target: T, source: Partial<T>): T => {
    const result = { ...target };
    
    for (const key in source) {
      if (source.hasOwnProperty(key)) {
        const value = source[key];
        if (value && typeof value === 'object' && !Array.isArray(value)) {
          result[key] = objectUtils.deepMerge(result[key] || {}, value);
        } else {
          result[key] = value as T[Extract<keyof T, string>];
        }
      }
    }
    
    return result;
  },

  /**
   * Remove propriedades undefined/null
   */
  compact: <T extends Record<string, any>>(obj: T): Partial<T> => {
    const result: Partial<T> = {};
    for (const key in obj) {
      if (obj[key] !== undefined && obj[key] !== null) {
        result[key] = obj[key];
      }
    }
    return result;
  },

  /**
   * Pega valor aninhado com path
   */
  get: <T>(obj: any, path: string, defaultValue?: T): T => {
    const keys = path.split('.');
    let result = obj;
    
    for (const key of keys) {
      if (result == null || typeof result !== 'object') {
        return defaultValue as T;
      }
      result = result[key];
    }
    
    return result !== undefined ? result : defaultValue;
  }
};
```

## Storage e Performance

### LocalStorage Utilities
```typescript
// storage.ts
export const storageUtils = {
  /**
   * Salva no localStorage com tratamento de erro
   */
  set: <T>(key: string, value: T): boolean => {
    try {
      localStorage.setItem(key, JSON.stringify(value));
      return true;
    } catch (error) {
      console.error('Erro ao salvar no localStorage:', error);
      return false;
    }
  },

  /**
   * Recupera do localStorage
   */
  get: <T>(key: string, defaultValue?: T): T | undefined => {
    try {
      const item = localStorage.getItem(key);
      return item ? JSON.parse(item) : defaultValue;
    } catch (error) {
      console.error('Erro ao ler do localStorage:', error);
      return defaultValue;
    }
  },

  /**
   * Remove do localStorage
   */
  remove: (key: string): boolean => {
    try {
      localStorage.removeItem(key);
      return true;
    } catch (error) {
      console.error('Erro ao remover do localStorage:', error);
      return false;
    }
  },

  /**
   * Limpa todo o localStorage
   */
  clear: (): boolean => {
    try {
      localStorage.clear();
      return true;
    } catch (error) {
      console.error('Erro ao limpar localStorage:', error);
      return false;
    }
  }
};
```

### Debounce e Throttle
```typescript
// debounce.ts
export const debounce = <T extends (...args: any[]) => any>(
  func: T,
  delay: number
): ((...args: Parameters<T>) => void) => {
  let timeoutId: NodeJS.Timeout;
  
  return (...args: Parameters<T>) => {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func(...args), delay);
  };
};

export const throttle = <T extends (...args: any[]) => any>(
  func: T,
  delay: number
): ((...args: Parameters<T>) => void) => {
  let lastCall = 0;
  
  return (...args: Parameters<T>) => {
    const now = Date.now();
    if (now - lastCall >= delay) {
      lastCall = now;
      func(...args);
    }
  };
};
```

## Exportações

```typescript
// index.ts
export * from './formatters';
export * from './date';
export * from './currency';
export * from './validators';
export * from './rules';
export * from './gameRules';
export * from './arrays';
export * from './objects';
export * from './storage';
export * from './debounce';
export * from './math';
export * from './file';
export * from './url';
export * from './clipboard';
```
