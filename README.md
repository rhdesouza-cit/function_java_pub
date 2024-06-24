# Projeto: Prova de Conceito (PoC) - Consumo de Serviço REST e Publicação em Tópico Pub/Sub
Este projeto é uma Prova de Conceito (PoC) que tem como objetivo avaliar a viabilidade e a eficácia de consumir dados de um serviço REST e publicar esses dados em um tópico do Google Cloud Pub/Sub. Além disso, este projeto irá considerar a utilização de uma Google Cloud Function para realizar essas operações de maneira eficiente e escalável.

## Objetivos
<b>Consumo de Serviço REST: </b>Desenvolver uma solução para consumir dados de um serviço REST.  
<b>Publicação em Tópico Pub/Sub: </b>Publicar os dados consumidos no serviço REST em um tópico do Google Cloud Pub/Sub.  
<b>Avaliação do Uso de Google Cloud Function: </b>Determinar se a utilização de uma Google Cloud Function é uma abordagem adequada para esta solução, considerando aspectos como custo, desempenho e escalabilidade.

## Componentes
<b>Serviço REST:</b> A fonte de dados que será consumida.  
<b>Google Cloud Pub/Sub:</b> Plataforma de mensagens que receberá os dados consumidos.  
<b>Google Cloud Function:</b> Função que pode ser utilizada para integrar o consumo do serviço REST e a publicação no Pub/Sub.

## Pré-requisitos
Conta no Google Cloud Platform (GCP)  
Habilitação das APIs necessárias: Cloud Functions, Pub/Sub, etc.  
Configuração de um tópico no Pub/Sub  
Endpoint do serviço REST para consumo

## Instruções de Uso
* <b>Configuração do Ambiente:</b>  
  Crie um tópico no Pub/Sub.  
  Habilite as APIs necessárias no GCP.
* <b>Projeto Spring:</b>  
  Insira as variáveis de ambiente PROJECT_ID, EMULATOR_HOST e BULLLA_PLOOMES_MS_URL  
  Execute o projeto com o curl:  ``curl --location 'http://localhost:8082/' \
  --header 'Content-Type: text/plain' \
  --data 'topic1'``

## Ferramentas utilizadas
* Linguagem principal: Java 21
* Gerenciador de dependências: Maven
* IDE: IntelliJ
* GCP: Pub/Sub Emulator
* Mock Server: Json Server

### Documentação de apoio:
* Json Server:  
  https://www.npmjs.com/package/json-server


* Pub/Sub emulator:  
  https://cloud.google.com/pubsub/docs/emulator?hl=pt-br#pubsub-emulator-java  
  https://cloud.google.com/sdk/gcloud/reference/beta/emulators/pubsub/start


* Configurações cloud spring:  
  https://cloud.spring.io/spring-cloud-static/spring-cloud-gcp/current/reference/html/appendix.html  
  https://cloud.spring.io/spring-cloud-static/spring-cloud-gcp/current/reference/html/#cloud-pubsub  
  https://docs.spring.io/spring-cloud-function/reference/spring-cloud-function/standalone-web-applications.html


* Implementação Cloud  
  https://cloud.google.com/functions/docs/deploy?hl=pt-br&cloudshell=false#basics

____________________________________________________________________
#### Nota: Este projeto é uma Prova de Conceito e pode necessitar de ajustes e otimizações antes de ser utilizado em ambientes de produção.
____________________________________________________________________