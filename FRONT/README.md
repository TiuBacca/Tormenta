# Front-end aplicação Controle de horas.

Essa aplicação foi desenvolvida em Angular 14.<br>
Angular CLI: 14.2.10<br>
Node: 16.14.1<br>
Package Manager: npm 8.19.2<br>

Para instalar o node/npm no Windows, visite https://nodejs.org/en/<br>
Para instalar o node no Linux, recomento o "nvm linux", consulte em `https://www.treinaweb.com.br/blog/instalando-e-gerenciando-varias-versoes-do-node-js-com-nvm`<br>
<br>
Para instalar Angular CLI no Windows ou Linux `npm install -g @angular/cli`<br>
<br>
Se for a primeira vez que estiver rodando uma aplicação Angular na sua máquina Windows, possívelmente terá problemas com Politicas de permissão do Windows.<br>
Consulte na internet com a mensagem do erro.<br>

# Orientações para rodar a aplicação localmente

Clone a branch `front` do repositório:<br>
http://gitlab.sabium.info/demandasinternas/jira-cloud/controle-horas.git<br>

Com o terminal aberto na raiz do projeto, rode `npm install --force`.<br>
Em seguida `npm start` <br>

# Orientações adicionais.

O arquivo proxy.conf.json na raiz do projeto, serve para resolver problemas com CORS localmente.<br>
Não esqueca de informar lá, o endereço do back-end que vai consumir.<br>
No arquivo package.json, em scripts, start, está o seguinte comando, "ng serve --port 4200 --open --host 0.0.0.0 --disable-host-check --proxy-config proxy.conf.json",<br>
Ele serve para:<br>
Rodar a aplicação<br>
Informar que é na porta 4200, você pode alterar para porta que desejar.<br>
O --open --host 0.0.0.0 --disable-host-check é para permitir que outras pessoas conectadas a VPN da Sabium, possam abrir o projeto pelo seu IP dentro da VPN.<br>
E o --proxy-config proxy.conf.json é para rodar o arquivo proxy.conf.json.<br>
Se você quiser rodar a aplicação sem todo esse procedimento, altere no arquivo package.json, em scripts, start, para `ng serve` somente, ai não se esqueça de em environment.ts, informar a url do back-end diretamente lá.<br>

# Orientações Docker

Para gerar uma nova imagem Docker do projeto.<br>
`ng buil` da raiz do projeto<br>
`docker build . -t dockersabium.azurecr.io/web/img-controle-horas-front-end:x.x`, no x altere a versão da nova imagem.<br>
Esse comando acima, só gera uma nova imagem.<br>
Para subir a nova imagem para o repositório da Sabium, rode:<br>
`docker push dockersabium.azurecr.io/web/img-controle-horas-front-end:x.x`, onde x.x é a versão que criou.<br>
Se você nunca subiu imagens Docker para o repositório da Sabium, peça credenciais ao André Moura.<br>


# Abaixo, é o readme gerado por padrão pelo Angular

# ControleHoras

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.2.7.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


