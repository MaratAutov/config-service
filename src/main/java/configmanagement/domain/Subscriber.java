package configmanagement.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@ApiModel(description = "Подписчик")
public class Subscriber  implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Идентификатор", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(notes = "Версия", dataType = "Integer")
    private Integer version = 0;
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Наименование подписчика", dataType = "String")
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Описание подписчика", dataType = "String")
    private String description;

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Список подписок, на которые подписан данный подписант")
    private List<Subscription> subscriptions = new ArrayList<>();

}
