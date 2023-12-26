# Puissance4ApiApi

All URIs are relative to *http://localhost:4444*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getWinnerUsingGET**](Puissance4ApiApi.md#getWinnerUsingGET) | **GET** /api/puissance4/winner | Obtient le gagnant de la partie (ou null si la partie n&#39;est pas terminée)
[**initializeGameUsingPOST**](Puissance4ApiApi.md#initializeGameUsingPOST) | **POST** /api/puissance4/initialize | Initialise une nouvelle partie de Puissance 4
[**isGameEndedUsingGET**](Puissance4ApiApi.md#isGameEndedUsingGET) | **GET** /api/puissance4/ended | Vérifie si la partie est terminée
[**playPieceUsingPOST**](Puissance4ApiApi.md#playPieceUsingPOST) | **POST** /api/puissance4/play | Effectue un coup dans la colonne spécifiée


<a name="getWinnerUsingGET"></a>
# **getWinnerUsingGET**
> Joueur getWinnerUsingGET()

Obtient le gagnant de la partie (ou null si la partie n&#39;est pas terminée)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Puissance4ApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    Puissance4ApiApi apiInstance = new Puissance4ApiApi(defaultClient);
    try {
      Joueur result = apiInstance.getWinnerUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Puissance4ApiApi#getWinnerUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Joueur**](Joueur.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="initializeGameUsingPOST"></a>
# **initializeGameUsingPOST**
> initializeGameUsingPOST(nomJoueur1, nomJoueur2)

Initialise une nouvelle partie de Puissance 4

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Puissance4ApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    Puissance4ApiApi apiInstance = new Puissance4ApiApi(defaultClient);
    String nomJoueur1 = "nomJoueur1_example"; // String | nomJoueur1
    String nomJoueur2 = "nomJoueur2_example"; // String | nomJoueur2
    try {
      apiInstance.initializeGameUsingPOST(nomJoueur1, nomJoueur2);
    } catch (ApiException e) {
      System.err.println("Exception when calling Puissance4ApiApi#initializeGameUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nomJoueur1** | **String**| nomJoueur1 |
 **nomJoueur2** | **String**| nomJoueur2 |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="isGameEndedUsingGET"></a>
# **isGameEndedUsingGET**
> Boolean isGameEndedUsingGET()

Vérifie si la partie est terminée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Puissance4ApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    Puissance4ApiApi apiInstance = new Puissance4ApiApi(defaultClient);
    try {
      Boolean result = apiInstance.isGameEndedUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Puissance4ApiApi#isGameEndedUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Boolean**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="playPieceUsingPOST"></a>
# **playPieceUsingPOST**
> Integer playPieceUsingPOST(column)

Effectue un coup dans la colonne spécifiée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Puissance4ApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    Puissance4ApiApi apiInstance = new Puissance4ApiApi(defaultClient);
    Integer column = 56; // Integer | column
    try {
      Integer result = apiInstance.playPieceUsingPOST(column);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Puissance4ApiApi#playPieceUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **column** | **Integer**| column |

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

