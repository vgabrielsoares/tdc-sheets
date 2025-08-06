# Hooks - Custom React Hooks

## Visão Geral
Custom hooks reutilizáveis para encapsular lógica de estado, efeitos colaterais e interações complexas na aplicação TDC Sheets.

## Categorias de Hooks

### Estado e Dados
- **useAuth** - Gerenciamento de autenticação
- **useFichas** - Operações com fichas
- **useLocalStorage** - Persistência local
- **useApi** - Chamadas de API genéricas

### Interface e UX
- **useModal** - Controle de modais
- **useToast** - Notificações toast
- **useTheme** - Tema claro/escuro
- **useBreakpoint** - Responsive design

### Formulários e Validação
- **useForm** - Gerenciamento de formulários
- **useValidation** - Validação de campos
- **useDebounce** - Debounce para inputs
- **useCalculos** - Cálculos de atributos

### Performance e Utilidades
- **useAsync** - Estados de requisições assíncronas
- **useIntersection** - Intersection Observer
- **useKeyboard** - Atalhos de teclado
- **useClipboard** - Operações de clipboard

## Hooks de Autenticação

### useAuth
```typescript
interface AuthState {
  user: User | null;
  token: string | null;
  isLoading: boolean;
  isAuthenticated: boolean;
}

interface AuthActions {
  login: (credentials: LoginCredentials) => Promise<void>;
  logout: () => void;
  register: (userData: RegisterData) => Promise<void>;
  refreshToken: () => Promise<void>;
}

export const useAuth = (): AuthState & AuthActions => {
  const dispatch = useAppDispatch();
  const { user, token, isLoading } = useAppSelector(state => state.auth);

  const login = useCallback(async (credentials: LoginCredentials) => {
    try {
      dispatch(authActions.setLoading(true));
      const response = await authService.login(credentials);
      dispatch(authActions.setUser(response.user));
      dispatch(authActions.setToken(response.token));
    } catch (error) {
      dispatch(authActions.setError(error.message));
      throw error;
    } finally {
      dispatch(authActions.setLoading(false));
    }
  }, [dispatch]);

  const logout = useCallback(() => {
    dispatch(authActions.logout());
    localStorage.removeItem('token');
  }, [dispatch]);

  return {
    user,
    token,
    isLoading,
    isAuthenticated: !!token,
    login,
    logout,
    register,
    refreshToken
  };
};
```

## Hooks de Fichas

### useFichas
```typescript
interface FichasHook {
  fichas: Ficha[];
  fichaAtual: Ficha | null;
  isLoading: boolean;
  error: string | null;
  criarFicha: (ficha: Partial<Ficha>) => Promise<Ficha>;
  atualizarFicha: (id: number, updates: Partial<Ficha>) => Promise<void>;
  excluirFicha: (id: number) => Promise<void>;
  carregarFicha: (id: number) => Promise<void>;
  compartilharFicha: (id: number, options: ShareOptions) => Promise<string>;
}

export const useFichas = (): FichasHook => {
  const dispatch = useAppDispatch();
  const { fichas, fichaAtual, isLoading, error } = useAppSelector(state => state.fichas);

  const criarFicha = useCallback(async (fichaData: Partial<Ficha>) => {
    dispatch(fichasActions.setLoading(true));
    try {
      const novaFicha = await fichaService.criar(fichaData);
      dispatch(fichasActions.adicionarFicha(novaFicha));
      return novaFicha;
    } catch (error) {
      dispatch(fichasActions.setError(error.message));
      throw error;
    } finally {
      dispatch(fichasActions.setLoading(false));
    }
  }, [dispatch]);

  return {
    fichas,
    fichaAtual,
    isLoading,
    error,
    criarFicha,
    atualizarFicha,
    excluirFicha,
    carregarFicha,
    compartilharFicha
  };
};
```

### useCalculos
```typescript
interface CalculosHook {
  calcularPV: (ficha: Partial<Ficha>) => number;
  calcularPP: (ficha: Partial<Ficha>) => number;
  calcularDefesa: (ficha: Partial<Ficha>) => number;
  validarAtributos: (atributos: Atributos) => ValidationResult;
  aplicarModificadores: (base: number, modificadores: Modificador[]) => number;
}

export const useCalculos = (): CalculosHook => {
  const calcularPV = useCallback((ficha: Partial<Ficha>) => {
    const { atributos, arquetipo, nivel = 1 } = ficha;
    if (!atributos || !arquetipo) return 0;

    const basePV = arquetipo.pvInicial;
    const bonusConstituicao = Math.floor((atributos.constituicao - 10) / 2);
    const pvPorNivel = arquetipo.pvPorNivel * (nivel - 1);

    return basePV + bonusConstituicao + pvPorNivel;
  }, []);

  const calcularPP = useCallback((ficha: Partial<Ficha>) => {
    const { atributos, arquetipo, nivel = 1 } = ficha;
    if (!atributos || !arquetipo) return 0;

    const basePP = arquetipo.ppInicial;
    const bonusPresenca = Math.floor((atributos.presenca - 10) / 2);
    const ppPorNivel = arquetipo.ppPorNivel * (nivel - 1);

    return Math.max(0, basePP + bonusPresenca + ppPorNivel);
  }, []);

  return {
    calcularPV,
    calcularPP,
    calcularDefesa,
    validarAtributos,
    aplicarModificadores
  };
};
```

## Hooks de Interface

### useModal
```typescript
interface ModalHook {
  isOpen: boolean;
  open: () => void;
  close: () => void;
  toggle: () => void;
}

export const useModal = (initialState = false): ModalHook => {
  const [isOpen, setIsOpen] = useState(initialState);

  const open = useCallback(() => setIsOpen(true), []);
  const close = useCallback(() => setIsOpen(false), []);
  const toggle = useCallback(() => setIsOpen(prev => !prev), []);

  // Fechar modal com ESC
  useEffect(() => {
    const handleEscape = (event: KeyboardEvent) => {
      if (event.key === 'Escape' && isOpen) {
        close();
      }
    };

    document.addEventListener('keydown', handleEscape);
    return () => document.removeEventListener('keydown', handleEscape);
  }, [isOpen, close]);

  return { isOpen, open, close, toggle };
};
```

### useToast
```typescript
interface ToastHook {
  showToast: (message: string, type?: ToastType) => void;
  showSuccess: (message: string) => void;
  showError: (message: string) => void;
  showWarning: (message: string) => void;
  showInfo: (message: string) => void;
}

export const useToast = (): ToastHook => {
  const dispatch = useAppDispatch();

  const showToast = useCallback((message: string, type: ToastType = 'info') => {
    const id = Math.random().toString(36).substr(2, 9);
    dispatch(uiActions.addToast({ id, message, type }));

    // Auto-remover após 5 segundos
    setTimeout(() => {
      dispatch(uiActions.removeToast(id));
    }, 5000);
  }, [dispatch]);

  const showSuccess = useCallback((message: string) => showToast(message, 'success'), [showToast]);
  const showError = useCallback((message: string) => showToast(message, 'error'), [showToast]);
  const showWarning = useCallback((message: string) => showToast(message, 'warning'), [showToast]);
  const showInfo = useCallback((message: string) => showToast(message, 'info'), [showToast]);

  return { showToast, showSuccess, showError, showWarning, showInfo };
};
```

## Hooks de Formulários

### useForm
```typescript
interface FormHook<T> {
  values: T;
  errors: Partial<Record<keyof T, string>>;
  touched: Partial<Record<keyof T, boolean>>;
  isValid: boolean;
  isSubmitting: boolean;
  setValue: (name: keyof T, value: any) => void;
  setError: (name: keyof T, error: string) => void;
  setTouched: (name: keyof T, touched: boolean) => void;
  handleSubmit: (onSubmit: (values: T) => void | Promise<void>) => (e: FormEvent) => void;
  reset: () => void;
}

export const useForm = <T extends Record<string, any>>(
  initialValues: T,
  validationSchema?: ValidationSchema<T>
): FormHook<T> => {
  const [values, setValues] = useState<T>(initialValues);
  const [errors, setErrors] = useState<Partial<Record<keyof T, string>>>({});
  const [touched, setTouched] = useState<Partial<Record<keyof T, boolean>>>({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const setValue = useCallback((name: keyof T, value: any) => {
    setValues(prev => ({ ...prev, [name]: value }));
    
    // Validar campo se já foi tocado
    if (touched[name] && validationSchema) {
      const fieldValidation = validationSchema[name];
      if (fieldValidation) {
        const error = fieldValidation(value);
        setErrors(prev => ({ ...prev, [name]: error }));
      }
    }
  }, [touched, validationSchema]);

  const handleSubmit = useCallback((onSubmit: (values: T) => void | Promise<void>) => {
    return async (e: FormEvent) => {
      e.preventDefault();
      setIsSubmitting(true);

      try {
        // Validar todos os campos
        if (validationSchema) {
          const newErrors: Partial<Record<keyof T, string>> = {};
          let hasErrors = false;

          for (const [field, validator] of Object.entries(validationSchema)) {
            const error = validator(values[field as keyof T]);
            if (error) {
              newErrors[field as keyof T] = error;
              hasErrors = true;
            }
          }

          setErrors(newErrors);
          if (hasErrors) return;
        }

        await onSubmit(values);
      } finally {
        setIsSubmitting(false);
      }
    };
  }, [values, validationSchema]);

  return {
    values,
    errors,
    touched,
    isValid: Object.keys(errors).length === 0,
    isSubmitting,
    setValue,
    setError: useCallback((name: keyof T, error: string) => {
      setErrors(prev => ({ ...prev, [name]: error }));
    }, []),
    setTouched: useCallback((name: keyof T, touched: boolean) => {
      setTouched(prev => ({ ...prev, [name]: touched }));
    }, []),
    handleSubmit,
    reset: useCallback(() => {
      setValues(initialValues);
      setErrors({});
      setTouched({});
    }, [initialValues])
  };
};
```

## Hooks de Performance

### useDebounce
```typescript
export const useDebounce = <T>(value: T, delay: number): T => {
  const [debouncedValue, setDebouncedValue] = useState<T>(value);

  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedValue(value);
    }, delay);

    return () => {
      clearTimeout(handler);
    };
  }, [value, delay]);

  return debouncedValue;
};
```

### useAsync
```typescript
interface AsyncState<T> {
  data: T | null;
  loading: boolean;
  error: Error | null;
}

interface AsyncHook<T> extends AsyncState<T> {
  execute: () => Promise<void>;
  reset: () => void;
}

export const useAsync = <T>(
  asyncFunction: () => Promise<T>,
  immediate = true
): AsyncHook<T> => {
  const [state, setState] = useState<AsyncState<T>>({
    data: null,
    loading: immediate,
    error: null
  });

  const execute = useCallback(async () => {
    setState(prev => ({ ...prev, loading: true, error: null }));
    
    try {
      const result = await asyncFunction();
      setState({ data: result, loading: false, error: null });
    } catch (error) {
      setState({ data: null, loading: false, error: error as Error });
    }
  }, [asyncFunction]);

  useEffect(() => {
    if (immediate) {
      execute();
    }
  }, [execute, immediate]);

  const reset = useCallback(() => {
    setState({ data: null, loading: false, error: null });
  }, []);

  return { ...state, execute, reset };
};
```

## Hooks Utilitários

### useLocalStorage
```typescript
export const useLocalStorage = <T>(
  key: string,
  initialValue: T
): [T, (value: T | ((val: T) => T)) => void] => {
  const [storedValue, setStoredValue] = useState<T>(() => {
    try {
      const item = window.localStorage.getItem(key);
      return item ? JSON.parse(item) : initialValue;
    } catch (error) {
      console.error(`Erro ao ler localStorage key "${key}":`, error);
      return initialValue;
    }
  });

  const setValue = useCallback((value: T | ((val: T) => T)) => {
    try {
      const valueToStore = value instanceof Function ? value(storedValue) : value;
      setStoredValue(valueToStore);
      window.localStorage.setItem(key, JSON.stringify(valueToStore));
    } catch (error) {
      console.error(`Erro ao salvar no localStorage key "${key}":`, error);
    }
  }, [key, storedValue]);

  return [storedValue, setValue];
};
```

### useBreakpoint
```typescript
type Breakpoint = 'sm' | 'md' | 'lg' | 'xl';

export const useBreakpoint = (): Breakpoint => {
  const [breakpoint, setBreakpoint] = useState<Breakpoint>('lg');

  useEffect(() => {
    const updateBreakpoint = () => {
      const width = window.innerWidth;
      if (width < 640) setBreakpoint('sm');
      else if (width < 768) setBreakpoint('md');
      else if (width < 1024) setBreakpoint('lg');
      else setBreakpoint('xl');
    };

    updateBreakpoint();
    window.addEventListener('resize', updateBreakpoint);
    return () => window.removeEventListener('resize', updateBreakpoint);
  }, []);

  return breakpoint;
};
```

## Testes

### Testing com React Testing Library
```typescript
// __tests__/useAuth.test.ts
import { renderHook, act } from '@testing-library/react';
import { useAuth } from '../useAuth';

describe('useAuth', () => {
  it('deve fazer login corretamente', async () => {
    const { result } = renderHook(() => useAuth());

    await act(async () => {
      await result.current.login({
        email: 'test@example.com',
        password: 'password123'
      });
    });

    expect(result.current.isAuthenticated).toBe(true);
    expect(result.current.user).toBeDefined();
  });
});
```

## Melhores Práticas

### Performance
- Use `useCallback` e `useMemo` apropriadamente
- Evite dependências desnecessárias em arrays de dependência
- Considere `useRef` para valores que não devem causar re-render

### Tipagem
- Sempre use TypeScript com tipos explícitos
- Crie interfaces claras para retornos de hooks
- Use generics quando apropriado

### Reutilização
- Mantenha hooks focados em uma responsabilidade
- Extraia lógica comum para hooks utilitários
- Documente comportamentos específicos
