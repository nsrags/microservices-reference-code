db.products.insertMany([
{
name:"Men T-Shirt",
productId:"prod10001",
description:"Men Polo T-Shirt",
isActive:true,
parentCategory:"cat10001",
childSkus:[
{
    skuId: "Sku10001",
    name: "Polo T-Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10002",
    name: "Polo T-Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10003",
    name: "Polo T-Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10004",
    name: "Polo T-Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]
},
{
name:"Men's Shirt'",
productId:"prod10002",
description:"Men Full Sleeve Shirt",
isActive:true,
parentCategory:"cat10001",
childSkus:[
{
    skuId: "Sku10005",
    name: "Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10006",
    name: "Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10007",
    name: "Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10008",
    name: "Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]
},
{
name:"Men's Casual T-Shirt ",
productId:"prod10003",
description:"Men Round Neck T-Shirt",
isActive:true,
parentCategory:"cat10001",
childSkus:[
{
    skuId: "Sku10006",
    name: "T-Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10007",
    name: "T-Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10008",
    name: "T-Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10009",
    name: "T-Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]
},
{
name:"Women's Shirt",
productId:"prod10004",
description:"Women  Full Sleeve Shirt",
isActive:true,
parentCategory:"cat10002",
childSkus:[
{
    skuId: "Sku10010",
    name: "Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10011",
    name: "Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10012",
    name: "Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10013",
    name: "Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]
},
{
name:"Women's  T-Shirt",
productId:"prod10005",
description:"Women Polo T-Shirt",
isActive:true,
parentCategory:"cat10002",
childSkus:[
{
    skuId: "Sku10014",
    name: "Polo T-Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10015",
    name: "Polo T-Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10016",
    name: "Polo T-Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10017",
    name: "Polo T-Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]

},
{
name:"Women's Casual T-Shirt",
productId:"prod10006",
description:"Women Round Neck T-Shirt",
isActive:true,
parentCategory:"cat10002",
childSkus:[
{
    skuId: "Sku10018",
    name: "T-Shirt Blue and XL",
    attributes:{
        color: "Blue",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10019",
    name: "T-Shirt Blue and Large",
    attributes:{
        color: "Blue",
        size: "Large"
    },
    isActive: true
},
{
    skuId: "Sku10020",
    name: "T-Shirt Red and XL",
    attributes:{
        color: "Red",
        size: "XL"
    },
    isActive: true
},
{
    skuId: "Sku10021",
    name: "T-Shirt Red  and Large",
    attributes:{
        color: "Red",
        size: "Large"
    },
    isActive: true
}
]
}
]);
