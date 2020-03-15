package configmanagement.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class Subscriber {
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Integer id;
    private Integer version = 1;
    @EqualsAndHashCode.Exclude
    @JsonAlias()
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String description;

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Subscription> subscriptions = new ArrayList<>();

}
