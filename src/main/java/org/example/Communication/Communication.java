package org.example.Communication;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String cookie;

    public String getCookie() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        return headers.getFirst(headers.SET_COOKIE);
    }


//    public String getAllUser() {
//        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
//        List<User> allUser = responseEntity.getBody();
//        HttpHeaders headers = responseEntity.getHeaders();
//        System.out.println(headers);
//        return headers.getFirst("Set-Cookie");
//    }

    public void saveUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(long id, HttpEntity<User> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }
}
