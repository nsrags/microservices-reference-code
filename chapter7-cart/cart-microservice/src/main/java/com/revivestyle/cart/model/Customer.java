package com.revivestyle.cart.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer {
    @Setter(AccessLevel.NONE)
    @NonNull
    private Long customerId;
    private String emailAddress;
}
