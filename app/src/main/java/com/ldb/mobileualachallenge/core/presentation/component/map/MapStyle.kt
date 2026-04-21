package com.ldb.mobileualachallenge.core.presentation.component.map


object MapStyle {

    /**
     * Defines the style in JSON string.
     * In the future this could be moved into res as a raw resource.
     */
    val json = """
        {
          "variant": "light",
          "styles": [
            {
              "id": "infrastructure.building",
              "geometry": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest",
              "geometry": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest.entertainment",
              "label": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest.foodAndDrink",
              "label": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest.other",
              "geometry": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest.recreation",
              "geometry": {
                "visible": false
              }
            },
            {
              "id": "pointOfInterest.retail",
              "label": {
                "visible": false
              }
            },
            {
              "id": "political.neighborhood",
              "label": {
                "visible": false
              }
            },
            {
              "id": "political.sublocality",
              "label": {
                "visible": false
              }
            }
          ]
        }
    """.trimIndent()
}