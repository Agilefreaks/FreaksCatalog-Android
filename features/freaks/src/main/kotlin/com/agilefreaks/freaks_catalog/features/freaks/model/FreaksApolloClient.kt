package com.agilefreaks.freaks_catalog.features.freaks.model

import com.apollographql.apollo.ApolloClient

val apolloClient: ApolloClient = ApolloClient.builder()
    .serverUrl("https://freaks-catalog.herokuapp.com/graphql")
    .build()

val apolloClient2: ApolloClient = ApolloClient.builder()
    .serverUrl("https://freaks-catalog.herokuapp.com/graphql")
    .build()
