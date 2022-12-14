package com.leroy.magmobile.api.data.address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CellData {
    private String id;
    private String code;
    private String shelf;
    private Integer position;
    private Integer type;
    private Integer standId;
    private Integer productsCount;

    public CellData(int position, int type, String shelf) {
        this.position = position;
        this.type = type;
        this.shelf = shelf;
    }
}
