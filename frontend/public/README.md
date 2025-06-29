# Public - Arquivos Estáticos Frontend

## Visão Geral
Diretório de arquivos estáticos públicos servidos diretamente pelo servidor web. Contém recursos que não passam pelo processo de build do Vite.

## Estrutura de Arquivos

```
public/
├── favicon.ico              # Ícone principal do site
├── manifest.json           # Web App Manifest (PWA)
├── robots.txt              # Diretrizes para web crawlers
├── sitemap.xml             # Mapa do site para SEO
├── icons/                  # Ícones em diferentes tamanhos
│   ├── icon-192x192.png   # Ícone para Android
│   ├── icon-512x512.png   # Ícone para splash screen
│   └── apple-touch-icon.png # Ícone para iOS
├── images/                 # Imagens estáticas
│   ├── logo/              # Logotipos e variações
│   ├── backgrounds/       # Imagens de fundo
│   ├── placeholders/      # Imagens placeholder
│   └── og/                # Open Graph images
├── fonts/                  # Fontes customizadas (se houver)
└── locales/               # Arquivos de tradução (i18n)
    ├── pt-BR.json
    └── en-US.json
```

## Configurações PWA

### manifest.json
```json
{
  "name": "TDC Sheets - Tabuleiro do Caos",
  "short_name": "TDC Sheets",
  "description": "Sistema de fichas para RPG Tabuleiro do Caos",
  "start_url": "/",
  "display": "standalone",
  "background_color": "#ffffff",
  "theme_color": "#2563eb",
  "orientation": "portrait-primary",
  "icons": [
    {
      "src": "/icons/icon-192x192.png",
      "sizes": "192x192",
      "type": "image/png",
      "purpose": "any maskable"
    },
    {
      "src": "/icons/icon-512x512.png",
      "sizes": "512x512",
      "type": "image/png",
      "purpose": "any"
    }
  ],
  "screenshots": [
    {
      "src": "/images/screenshots/desktop.png",
      "sizes": "1280x720",
      "type": "image/png",
      "form_factor": "wide"
    },
    {
      "src": "/images/screenshots/mobile.png",
      "sizes": "375x667",
      "type": "image/png",
      "form_factor": "narrow"
    }
  ]
}
```

## SEO e Meta Tags

### robots.txt
```
User-agent: *
Allow: /
Disallow: /api/
Disallow: /admin/
Disallow: /_private/

Sitemap: https://tdc-sheets.com/sitemap.xml
```

### sitemap.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
  <url>
    <loc>https://tdc-sheets.com/</loc>
    <lastmod>2025-06-29</lastmod>
    <changefreq>weekly</changefreq>
    <priority>1.0</priority>
  </url>
  <url>
    <loc>https://tdc-sheets.com/about</loc>
    <lastmod>2025-06-29</lastmod>
    <changefreq>monthly</changefreq>
    <priority>0.8</priority>
  </url>
</urlset>
```

## Ícones e Assets

### Especificações de Ícones
- **favicon.ico**: 16x16, 32x32, 48x48 (multi-size)
- **icon-192x192.png**: Android home screen
- **icon-512x512.png**: Splash screen e app drawer
- **apple-touch-icon.png**: 180x180 para iOS

### Diretrizes de Design
- **Estilo**: Minimalista, alinhado com identidade visual
- **Cores**: Paleta primária do TDC Sheets
- **Formato**: PNG com transparência para ícones
- **Otimização**: Compressão otimizada para web

## Imagens Estáticas

### Logo e Branding
```
images/logo/
├── logo-full.svg           # Logo completo vetorial
├── logo-icon.svg          # Apenas ícone
├── logo-text.svg          # Apenas texto
├── logo-white.svg         # Versão para fundos escuros
└── logo-monochrome.svg    # Versão monocromática
```

### Placeholders
```
images/placeholders/
├── avatar-default.png     # Avatar padrão de usuário
├── ficha-empty.svg       # Placeholder para fichas vazias
└── image-loading.svg     # Loading state para imagens
```

### Open Graph
```
images/og/
├── og-default.png        # Imagem padrão para redes sociais
├── og-home.png          # Página inicial
└── og-ficha.png         # Template para fichas compartilhadas
```

## Internacionalização

### Estrutura de Tradução
```json
// locales/pt-BR.json
{
  "common": {
    "loading": "Carregando...",
    "save": "Salvar",
    "cancel": "Cancelar",
    "delete": "Excluir",
    "edit": "Editar"
  },
  "ficha": {
    "title": "Ficha de Personagem",
    "attributes": "Atributos",
    "skills": "Habilidades",
    "equipment": "Equipamentos"
  },
  "validation": {
    "required": "Campo obrigatório",
    "minLength": "Mínimo {{min}} caracteres",
    "maxLength": "Máximo {{max}} caracteres"
  }
}
```

### Fallbacks
```json
// locales/en-US.json
{
  "common": {
    "loading": "Loading...",
    "save": "Save",
    "cancel": "Cancel",
    "delete": "Delete",
    "edit": "Edit"
  }
}
```

## Performance e Otimização

### Compressão de Imagens
- **WebP**: Formato preferencial para navegadores modernos
- **Fallback**: PNG/JPEG para compatibilidade
- **Lazy Loading**: Implementado via `loading="lazy"`
- **Responsive**: Diferentes tamanhos para diferentes telas

### Caching
```
# Headers de cache para nginx
location /icons/ {
    expires 1y;
    add_header Cache-Control "public, immutable";
}

location /images/ {
    expires 6M;
    add_header Cache-Control "public";
}
```

## Service Worker

### Cache Strategy
```javascript
// sw.js (será gerado pelo Vite PWA)
const CACHE_NAME = 'tdc-sheets-v1';
const STATIC_ASSETS = [
  '/icons/icon-192x192.png',
  '/icons/icon-512x512.png',
  '/images/logo/logo-full.svg'
];

self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then((cache) => cache.addAll(STATIC_ASSETS))
  );
});
```

## Segurança

### Content Security Policy
```html
<!-- Headers para arquivos estáticos -->
<meta http-equiv="Content-Security-Policy" 
      content="img-src 'self' data: blob:; 
               font-src 'self';
               object-src 'none';">
```

### MIME Types
- Configuração correta de tipos MIME
- Prevenção de MIME type sniffing
- Headers de segurança apropriados

## Desenvolvimento

### Assets de Desenvolvimento
```
public/_dev/
├── mock-data/              # Dados mock para desenvolvimento
├── test-images/           # Imagens para testes
└── style-guide/          # Guia de estilos visual
```

### Validação
```bash
# Verificar otimização de imagens
npm run validate:images

# Verificar acessibilidade de ícones
npm run a11y:icons

# Validar manifest PWA
npm run validate:pwa
```

## Deploy

### Build Process
1. Otimização automática de imagens
2. Geração de diferentes formatos (WebP, AVIF)
3. Criação de favicons multi-tamanho
4. Validação de manifest.json

### CDN Integration
- Upload automático para CDN
- Versionamento de assets
- Invalidação de cache em deploy

## Manutenção

### Checklist Regular
- [ ] Verificar links quebrados em sitemap
- [ ] Atualizar screenshots para PWA
- [ ] Revisar e comprimir novas imagens
- [ ] Validar acessibilidade de ícones
- [ ] Testar manifest em diferentes dispositivos

### Monitoramento
- Lighthouse CI para performance
- Verificação automática de imagens quebradas
- Relatórios de uso de assets não utilizados
