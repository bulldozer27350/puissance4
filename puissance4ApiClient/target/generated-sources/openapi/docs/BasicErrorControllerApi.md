# BasicErrorControllerApi

All URIs are relative to *http://localhost:4444*

Method | HTTP request | Description
------------- | ------------- | -------------
[**errorUsingDELETE**](BasicErrorControllerApi.md#errorUsingDELETE) | **DELETE** /error | error
[**errorUsingGET**](BasicErrorControllerApi.md#errorUsingGET) | **GET** /error | error
[**errorUsingHEAD**](BasicErrorControllerApi.md#errorUsingHEAD) | **HEAD** /error | error
[**errorUsingOPTIONS**](BasicErrorControllerApi.md#errorUsingOPTIONS) | **OPTIONS** /error | error
[**errorUsingPATCH**](BasicErrorControllerApi.md#errorUsingPATCH) | **PATCH** /error | error
[**errorUsingPOST**](BasicErrorControllerApi.md#errorUsingPOST) | **POST** /error | error
[**errorUsingPUT**](BasicErrorControllerApi.md#errorUsingPUT) | **PUT** /error | error


<a name="errorUsingDELETE"></a>
# **errorUsingDELETE**
> Map&lt;String, Object&gt; errorUsingDELETE()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingDELETE();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingDELETE");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingGET"></a>
# **errorUsingGET**
> Map&lt;String, Object&gt; errorUsingGET()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingGET");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingHEAD"></a>
# **errorUsingHEAD**
> Map&lt;String, Object&gt; errorUsingHEAD()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingHEAD();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingHEAD");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingOPTIONS"></a>
# **errorUsingOPTIONS**
> Map&lt;String, Object&gt; errorUsingOPTIONS()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingOPTIONS();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingOPTIONS");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingPATCH"></a>
# **errorUsingPATCH**
> Map&lt;String, Object&gt; errorUsingPATCH()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingPATCH();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingPATCH");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingPOST"></a>
# **errorUsingPOST**
> Map&lt;String, Object&gt; errorUsingPOST()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingPOST();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingPOST");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="errorUsingPUT"></a>
# **errorUsingPUT**
> Map&lt;String, Object&gt; errorUsingPUT()

error

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BasicErrorControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:4444");

    BasicErrorControllerApi apiInstance = new BasicErrorControllerApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.errorUsingPUT();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BasicErrorControllerApi#errorUsingPUT");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

