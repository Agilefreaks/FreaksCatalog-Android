package com.agilefreaks.freaks_catalog.features.freaks

import com.apollographql.apollo.ApolloClient

val apolloClient: ApolloClient = ApolloClient.builder()
    .serverUrl("https://freaks-catalog.herokuapp.com/graphql")
    .build()
