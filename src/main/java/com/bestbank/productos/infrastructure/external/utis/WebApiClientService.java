package com.bestbank.productos.infrastructure.external.utis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WebApiClientService {

  private final Builder restClient;
  
  public WebApiClientService(Builder cliente) {
    this.restClient = cliente;
  }
  
  /**
   * Realiza una solicitud GET a la URL especificada y devuelve un Mono que emite la 
   * respuesta esperada.
   *
   * @param baseUrl      La URL base de resolucion de la peticion
   * @param url          La URL a la cual realizar la solicitud GET.
   * @param responseType El tipo de respuesta esperada.
   * @param errorId      Identificador de error personalizado para la gestión de excepciones.
   * @return Un Mono que emite la respuesta esperada.
   */
  public <T> Mono<T> getMono(String baseUrl, String url,
      Class<T> responseType, String errorId) {
    log.info(baseUrl + url);
    return restClient.baseUrl(baseUrl).build()
      .get()
      .uri(url)
      .retrieve()
      .bodyToMono(responseType)
      .doOnError(WebClientResponseException.class, e -> {
        HttpStatus statusCode = (HttpStatus) e.getStatusCode();
        log.error(
            String.format("Error Api = URL: %s ,CODIGO: %s ,CONTRLID: %s", url, 
                statusCode, errorId));
      });
  }
  
  
  public Mono<Object> callbakFuncHelper() {
    log.info("Fallback - Respost");
    return Mono.empty();
  }

  /**
   * Realiza una solicitud GET a la URL especificada y devuelve un Flux que emite la 
   * respuesta esperada.
   *
   * @param baseUrl      La URL base de resolucion de la peticion
   * @param url          La URL a la cual realizar la solicitud GET.
   * @param responseType El tipo de respuesta esperada.
   * @param errorId      Identificador de error personalizado para la gestión de excepciones.
   * @return Un Flux que emite la respuesta esperada.
   */
  public <T> Flux<T> getFlux(String baseUrl, String url, 
      Class<T> responseType, String errorId) {
    log.info(url);
    return restClient.baseUrl(baseUrl).build()
      .get()
      .uri(url)
      .retrieve()
      .bodyToFlux(responseType)
      .doOnError(WebClientResponseException.class, e -> {
        HttpStatus statusCode = (HttpStatus) e.getStatusCode();
        log.error(
            String.format("Error Api = URL: %s ,CODIGO: %s ,CONTRLID: %s",
                url, statusCode, errorId));
      });
  }
}
