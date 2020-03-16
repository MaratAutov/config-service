package configmanagement.controllers;

import configmanagement.domain.Parameter;
import configmanagement.domain.Subscriber;
import configmanagement.services.SubscriberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/subscribers")
@Api(value = "Сервис управления подписчиками", description = "Сервис управления подписчиками")
public class SubscriberController {
    private final SubscriberService service;

    @Autowired
    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    /**
     * @param subscriber
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить подписчика")
    public ResponseEntity<Subscriber> addSubscriber(@ApiParam(value = "Добавляемый подписчик") @RequestBody Subscriber subscriber) {
        return ResponseEntity.ok(service.addSubscriber(subscriber));
    }

    /**
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Subscriber>> getSubscribers() {
        return ResponseEntity.ok(service.getSubscribers());
    }

    /**
     * Получить подписчика по идентификатору
     *
     * @param id идентификатор подписчика
     * @return Найденная информация о подписчике, в противном случае {@code null}
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Получить подписчика по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Подписчик найден"),
            @ApiResponse(code = 404, message = "Подписчик не найден")
    })
    public ResponseEntity<Subscriber> getSubscriberById(
            @ApiParam(value = "Идентификатор подписчика") @PathVariable Integer id) {
        Subscriber subscriber = service.getSubcriberById(id);
        if (subscriber != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(subscriber);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<Subscriber> updateParameter(@RequestBody Subscriber subscriber) {
        return ResponseEntity.ok(service.updateSubscriber(subscriber));
    }

    /**
     * Удалить подписчика по идентификатору
     *
     * @param id идентификатор подписчика
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Удалить подписчика по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удаление произведено"),
            @ApiResponse(code = 404, message = "Подписчик не найден")
    })
    public ResponseEntity<?> deleteParameterById(
            @ApiParam(value = "Идентификатор подписчика") @PathVariable Integer id) {
        if (service.deleteSubscriberById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
