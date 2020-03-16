package configmanagement.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class LoadConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SortDescriptor> sortDescriptors = new ArrayList<>();
    private List<FilterDescriptor> filterDescriptors = new ArrayList<>();

    private int pageNumber;
    private int pageSize;
    private boolean disjunction;

}
