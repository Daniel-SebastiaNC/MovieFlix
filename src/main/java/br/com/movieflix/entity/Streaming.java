package br.com.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "tb_streaming")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Streaming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;
}
