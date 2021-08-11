# FreaksCatalog-Android
Android FreaksCatalog app

How to add and use Apollo 

Apollo Client CLI - global installation
npm install -g apollo

Gradle plugin and configuration (latest APOLLO_VERSION = "2.5.9")
plugins:
id("com.apollographql.apollo3").version("APOLLO_VERSION")
runtime dependencies:
implementation("com.apollographql.apollo:apollo-runtime:${PluginsVersions.APOLLO_VERSION}")
implementation("com.apollographql.apollo:apollo-coroutines-support:${PluginsVersions.APOLLO_VERSION}")

Apollo configuration - instruct the compiler to generate Kotlin models
apollo {
  generateKotlinModels.set(true)
}

How to download graphql schema :
apollo schema:download --endpoint="url" download_path
apollo schema:download --endpoint="https://freaks-catalog.herokuapp.com/graphql" features/freaks/src/main/graphql/com.agilefreaks.freaks_catalog.features.freaks/schema.json

Add JS GraphQL plugin to Android Studio (File-> Settings- > Plugins)
Android Studio will suggest to add a .graphqlconfig file, create the file and edit the schemaPath and url.
The config file should be next to the schema

Create a query file (add the .graphql extension to its name) and add the query inside

Inside the project create the apollo client
apolloClient: ApolloClient = ApolloClient.builder()
    .serverUrl("https://freaks-catalog.herokuapp.com/graphql")
    .build()

Build the project to create the new dependencies.
Make API calls using apolloClient.query(query_fileQuery() - this class will be generated automatically) to retrieve data.