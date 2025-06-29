# Services - Camada de Comunicação

## Visão Geral
Camada de serviços responsável pela comunicação com APIs externas, transformação de dados e integração com o backend.

## Estrutura de Serviços

### API Client
```typescript
class ApiClient {
  private baseURL: string;
  private token?: string;
  
  constructor(baseURL: string) {
    this.baseURL = baseURL;
  }
  
  setAuthToken(token: string) {
    this.token = token;
  }
  
  private async request<T>(
    endpoint: string, 
    options: RequestInit = {}
  ): Promise<T> {
    // Implementação do cliente HTTP
  }
}
```

### Serviços Principais

#### AuthService
- Login e logout
- Refresh de tokens
- Validação de sessão
- Registro de usuários

#### FichaService
- CRUD de fichas
- Compartilhamento
- Versionamento
- Busca e filtros

#### CalculosService
- Cálculos de atributos
- Validações de regras
- Fórmulas dinâmicas

#### UploadService
- Upload de imagens
- Gerenciamento de arquivos
- Compressão automática

## Interceptors

### Request Interceptor
```typescript
const requestInterceptor = (config: AxiosRequestConfig) => {
  // Adicionar token de autorização
  const token = getAuthToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  
  // Adicionar timestamp para cache busting
  config.params = {
    ...config.params,
    _t: Date.now()
  };
  
  return config;
};
```

### Response Interceptor
```typescript
const responseInterceptor = {
  onSuccess: (response: AxiosResponse) => {
    return response.data;
  },
  
  onError: (error: AxiosError) => {
    if (error.response?.status === 401) {
      // Token expirado - redirecionar para login
      redirectToLogin();
    }
    
    if (error.response?.status >= 500) {
      // Erro do servidor - mostrar notificação
      showErrorNotification('Erro interno do servidor');
    }
    
    return Promise.reject(error);
  }
};
```

## Cache Strategy

### Service Worker
- Cache de recursos estáticos
- Estratégia cache-first para assets
- Network-first para dados dinâmicos

### Memory Cache
```typescript
class CacheService {
  private cache = new Map<string, { data: any; timestamp: number }>();
  private ttl = 5 * 60 * 1000; // 5 minutos
  
  set(key: string, data: any) {
    this.cache.set(key, { data, timestamp: Date.now() });
  }
  
  get(key: string) {
    const cached = this.cache.get(key);
    if (!cached) return null;
    
    if (Date.now() - cached.timestamp > this.ttl) {
      this.cache.delete(key);
      return null;
    }
    
    return cached.data;
  }
}
```

## Error Handling

### Error Types
```typescript
enum ApiErrorType {
  NETWORK_ERROR = 'NETWORK_ERROR',
  VALIDATION_ERROR = 'VALIDATION_ERROR',
  AUTH_ERROR = 'AUTH_ERROR',
  SERVER_ERROR = 'SERVER_ERROR',
  TIMEOUT_ERROR = 'TIMEOUT_ERROR'
}

class ApiError extends Error {
  constructor(
    public type: ApiErrorType,
    public message: string,
    public statusCode?: number,
    public details?: any
  ) {
    super(message);
  }
}
```

### Retry Logic
```typescript
const retryConfig = {
  retries: 3,
  retryDelay: (retryCount: number) => Math.pow(2, retryCount) * 1000,
  retryCondition: (error: AxiosError) => {
    return error.code === 'NETWORK_ERROR' || 
           (error.response?.status >= 500);
  }
};
```

## WebSocket Integration

### Real-time Updates
```typescript
class WebSocketService {
  private ws?: WebSocket;
  private reconnectAttempts = 0;
  private maxReconnectAttempts = 5;
  
  connect() {
    this.ws = new WebSocket(WS_URL);
    
    this.ws.onopen = () => {
      console.log('WebSocket connected');
      this.reconnectAttempts = 0;
    };
    
    this.ws.onmessage = (event) => {
      const data = JSON.parse(event.data);
      this.handleMessage(data);
    };
    
    this.ws.onclose = () => {
      this.handleReconnection();
    };
  }
  
  private handleReconnection() {
    if (this.reconnectAttempts < this.maxReconnectAttempts) {
      setTimeout(() => {
        this.reconnectAttempts++;
        this.connect();
      }, Math.pow(2, this.reconnectAttempts) * 1000);
    }
  }
}
```

## Data Transformation

### DTOs
```typescript
interface FichaDTO {
  id: number;
  nomePersonagem: string;
  userId: number;
  // ... outros campos do backend
}

interface FichaModel {
  id: number;
  name: string;
  owner: number;
  // ... campos transformados para o frontend
}

const transformFichaDTO = (dto: FichaDTO): FichaModel => ({
  id: dto.id,
  name: dto.nomePersonagem,
  owner: dto.userId,
  // ... transformações necessárias
});
```

### Validation
```typescript
import { z } from 'zod';

const FichaSchema = z.object({
  name: z.string().min(1).max(100),
  level: z.number().min(1).max(20),
  attributes: z.object({
    strength: z.number().min(1).max(20),
    agility: z.number().min(1).max(20),
    // ... outros atributos
  })
});

const validateFicha = (data: unknown): FichaModel => {
  return FichaSchema.parse(data);
};
```

## Performance Optimization

### Request Batching
```typescript
class BatchService {
  private queue: Array<{ request: Promise<any>; resolve: Function; reject: Function }> = [];
  private batchTimeout: NodeJS.Timeout | null = null;
  
  addToBatch<T>(request: () => Promise<T>): Promise<T> {
    return new Promise((resolve, reject) => {
      this.queue.push({ request: request(), resolve, reject });
      
      if (!this.batchTimeout) {
        this.batchTimeout = setTimeout(() => {
          this.processBatch();
        }, 100);
      }
    });
  }
  
  private async processBatch() {
    const currentQueue = [...this.queue];
    this.queue = [];
    this.batchTimeout = null;
    
    // Processar requests em batch
    const results = await Promise.allSettled(
      currentQueue.map(item => item.request)
    );
    
    results.forEach((result, index) => {
      if (result.status === 'fulfilled') {
        currentQueue[index].resolve(result.value);
      } else {
        currentQueue[index].reject(result.reason);
      }
    });
  }
}
```
