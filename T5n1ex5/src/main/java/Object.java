import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
public class Object implements Serializable {
    private String name;
    private int price;
    private transient String description;
}
