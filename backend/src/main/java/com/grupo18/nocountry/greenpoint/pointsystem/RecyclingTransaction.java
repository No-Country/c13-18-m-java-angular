package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecyclingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="details_id", nullable=false)
    private RecyclableDetails recyclableDetails;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
