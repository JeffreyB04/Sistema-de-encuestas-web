package cr.ac.una.ejemplo28.resources;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    // JAX-RS define la interfaz ContainerResponseFilter, implementada por
    // los filtros de respuesta del contenedor. Normalmente, la instancia del
    // filtro se aplica globalmente a cualquier respuesta HTTP.
    // El filtro inyecta el encabezado Access-Control-Allow-Origin en cada
    // solicitud saliente y habilitará el mecanismo CORS.
    // Los filtros que implementan ContainerResponseFilter deben anotarse
    // explícitamente con @Provider para que JAX-RS los detecte en tiempo de
    // ejecución.
    // El encabezado 'Access-Control-Allow-Origin' con '*', indica que se puede
    // acceder a cualquier punto final (endpoint) de la URL en el servidor
    // a través de cualquier dominio.
    // Si es necesario restringir el acceso entre dominios explícitamente,
    // debemos mencionar ese dominio en este encabezado.
    //
    // https://www.baeldung.com/cors-in-jax-rs
    // https://javascript.info/fetch-crossorigin
    //
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}
