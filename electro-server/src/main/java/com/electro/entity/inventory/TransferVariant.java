package com.electro.entity.inventory;

import com.electro.entity.product.Variant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "transfer_variant")
public class TransferVariant {
    @EmbeddedId
    private TransferVariantKey transferVariantKey = new TransferVariantKey();

    @ManyToOne
    @MapsId("transferId")
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
