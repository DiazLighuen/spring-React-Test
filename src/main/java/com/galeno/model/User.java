package com.galeno.model;

        import lombok.Getter;
        import lombok.Setter;
        import org.hibernate.annotations.GenericGenerator;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import java.awt.*;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private String username;
    private Image userImage;
    private Boolean userVip;
}
