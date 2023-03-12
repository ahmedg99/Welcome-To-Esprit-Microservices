package tn.esprit.springfever.Services.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tn.esprit.springfever.DTO.UserDTO;
import tn.esprit.springfever.Services.Interfaces.IServiceUser;
import tn.esprit.springfever.Services.Interfaces.IUserService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {
    @Value("${spring.rabbitmq.template.exchange.forum}")
    private String rabbitmqExchange;


    @Value("${spring.rabbitmq.template.routing-key.forum.token}")
    private String rabbitmqRoutingKey;
    @Value("${spring.rabbitmq.template.routing-key.forum.id}")
    private String rabbitmqRoutingId;

    @Value("${spring.rabbitmq.template.routing-key.forum.ids}")
    private String rabbitmqRoutingIds;
    @Autowired
    private RabbitTemplate amqpTemplate;


    @Override
    public UserDTO getUserDetailsFromToken(String token) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(token.substring("Bearer ".length()));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .andProperties(messageProperties)
                .build();
        Message response = amqpTemplate.sendAndReceive(rabbitmqExchange, rabbitmqRoutingKey, message);
        UserDTO userDetails = null;
        if (response != null && response.getBody() != null && response.getBody().length > 0) {
            String jsonResponse = new String(response.getBody(), StandardCharsets.UTF_8);
            userDetails = objectMapper.readValue(jsonResponse, UserDTO.class);
        }
       return userDetails;
    }

    @Override
    public UserDTO getUserDetailsFromId(Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String token= String.valueOf(id);
        String json = objectMapper.writeValueAsString(token);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .andProperties(messageProperties)
                .build();
        Message response = amqpTemplate.sendAndReceive(rabbitmqExchange, rabbitmqRoutingId, message);
        UserDTO userDetails = null;
        if (response != null && response.getBody() != null && response.getBody().length > 0) {
            String jsonResponse = new String(response.getBody(), StandardCharsets.UTF_8);
            userDetails = objectMapper.readValue(jsonResponse, UserDTO.class);
        }
        return userDetails;
    }

    @Override
    public List<UserDTO> getUserDetailsFromIds(List<Long> list) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonStr = JSONArray.toJSONString(list);
        String json = objectMapper.writeValueAsString(jsonStr);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .andProperties(messageProperties)
                .build();
        Message response = amqpTemplate.sendAndReceive(rabbitmqExchange, rabbitmqRoutingIds, message);
        List<?> userDetails = null;
        List<UserDTO> userDTOS = new ArrayList<>();
        if (response != null && response.getBody() != null && response.getBody().length > 0) {
            String jsonResponse = new String(response.getBody(), StandardCharsets.UTF_8);
            userDetails = objectMapper.readValue(jsonResponse, List.class);
        }
        for (Object o : userDetails){
            String jsonString = objectMapper.writeValueAsString(o);
            UserDTO user = objectMapper.readValue(jsonString, UserDTO.class);
            userDTOS.add(user);
        }
        return userDTOS;
    }
}
