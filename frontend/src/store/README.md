# Store - Gerenciamento de Estado

## Visão Geral
Gerenciamento de estado global da aplicação utilizando Redux Toolkit com persistência e middleware otimizado.

## Arquitetura do Store

### Configuração Principal
```typescript
export const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    fichas: fichasSlice.reducer,
    ui: uiSlice.reducer,
    calculos: calculosSlice.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }).concat(apiSlice.middleware),
});
```

### Slices Principais

#### authSlice
- Estado de autenticação do usuário
- Informações do perfil
- Tokens JWT
- Permissões e roles

#### fichasSlice
- Lista de fichas do usuário
- Ficha atualmente selecionada
- Estado de loading e erros
- Filtros e busca

#### uiSlice
- Estado da interface (modais, sidebars)
- Tema claro/escuro
- Notificações
- Loading states globais

#### calculosSlice
- Cálculos de atributos em tempo real
- Cache de fórmulas
- Validações de regras do RPG

## RTK Query

### API Slice
```typescript
export const apiSlice = createApi({
  reducerPath: 'api',
  baseQuery: fetchBaseQuery({
    baseUrl: '/api',
    prepareHeaders: (headers, { getState }) => {
      const token = getState().auth.token;
      if (token) {
        headers.set('authorization', `Bearer ${token}`);
      }
      return headers;
    },
  }),
  tagTypes: ['Ficha', 'User'],
  endpoints: (builder) => ({
    // Endpoints aqui
  }),
});
```

### Cache e Invalidação
- Invalidação automática por tags
- Refetch em foco da janela
- Polling para dados em tempo real

## Persistência

### Redux Persist
```typescript
const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['auth', 'ui'], // Apenas auth e ui são persistidos
};
```

### Local Storage
- Token de autenticação
- Preferências do usuário
- Rascunhos de fichas

## Middleware Customizado

### Logger
- Logging de actions em desenvolvimento
- Rastreamento de performance
- Debug de estado

### Error Handling
- Captura e tratamento de erros
- Retry automático para falhas de rede
- Fallback gracioso

## Hooks Customizados

### useAuth
```typescript
export const useAuth = () => {
  const dispatch = useAppDispatch();
  const { user, token, isLoading } = useAppSelector(state => state.auth);
  
  const login = useCallback((credentials) => {
    return dispatch(authSlice.actions.login(credentials));
  }, [dispatch]);
  
  return { user, token, isLoading, login };
};
```

### useFichas
```typescript
export const useFichas = () => {
  const dispatch = useAppDispatch();
  const fichas = useAppSelector(state => state.fichas.list);
  
  const createFicha = useCallback((ficha) => {
    return dispatch(fichasSlice.actions.create(ficha));
  }, [dispatch]);
  
  return { fichas, createFicha };
};
```

## Performance

### Seletores Memoizados
```typescript
const selectFichasByUser = createSelector(
  [state => state.fichas.list, (state, userId) => userId],
  (fichas, userId) => fichas.filter(f => f.userId === userId)
);
```

### Code Splitting
- Lazy loading de slices
- Dynamic imports para reducers grandes
- Chunk splitting por feature

## DevTools

### Redux DevTools
- Time travel debugging
- Action replay
- State diff visualization

### Performance Monitoring
- Render tracking
- Action performance
- Memory usage
