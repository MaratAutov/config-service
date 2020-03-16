package configmanagement.controllers;

import configmanagement.domain.Subscriber;
import configmanagement.domain.Subscription;
import configmanagement.services.SubscriptionService;
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
@RequestMapping("/config/subscriptions")
@Api(value = "Сервис управления подписками", description = "Сервис управления подписками")
public class SubscriptionController {
    private final SubscriptionService service;

    @Autowired
    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    /**
     * @param subscription
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить подписку")
    public ResponseEntity<Subscription> addSubscriber(@ApiParam(value = "Добавляемая подписка") @RequestBody Subscription subscription) {
        return ResponseEntity.ok(service.addSuscription(subscription));
    }

    /**
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        return ResponseEntity.ok(service.getSubscriptions());
    }

    /**
     * Получить подписку по идентификатору
     *
     * @param id идентификатор подписки
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Получить подписку по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Подписка найдена"),
            @ApiResponse(code = 404, message = "Подписка не найдена")
    })
    public ResponseEntity<Subscription> getSubscriptionById(
            @ApiParam(value = "Идентификатор подписки") @PathVariable Integer id) {
        Subscription subscription = service.getSubscriptionById(id);
        if (subscription != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(subscription);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<Subscription> updateParameter(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(service.updateSubscription(subscription));
    }

    /**
     * Удалить подписку по идентификатору
     *
     * @param id идентификатор подписки
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Удалить подписку по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удаление произведено"),
            @ApiResponse(code = 404, message = "Подписчик не найден")
    })
    public ResponseEntity<?> deleteParameterById(
            @ApiParam(value = "Идентификатор подписки") @PathVariable Integer id) {
        if (service.deleteSubscriptionById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
