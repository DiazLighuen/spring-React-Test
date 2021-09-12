import { ProductStore } from "./store";
import { SpecialMonthStore } from "./store";
import { CartStore } from "./store";
import { CartsStore } from "./store";

const API_URL = "http://localhost:8080";

export const fetchCart = async (id) => {
    const response = await fetch(`${API_URL}/cart/getCart/${id}`);
    const data = await response.json();
    
    CartStore.update(s => { s.cart = data });

}

export const fetchSpecialMonth = async () => {

    const response = await fetch(`${ API_URL }/utils/specialMonth`);
    const data = await response.json();
    
    SpecialMonthStore.update(s => { s.specialMonth = data });
}

export const fetchProducts = async () => {

    const response = await fetch(`${ API_URL }/product`);
    const data = await response.json();
    
    ProductStore.update(s => { s.products = data });
}

export const addToCart = async (parameter) => {
    const requestOption = {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(parameter)
    };
    await fetch(`${ API_URL }/cart/add`, requestOption);
    fetchCart(parameter.userId);
}

export const payCart = async (parameter) => {
    const requestOption = {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(parameter)
    };
    await fetch(`${ API_URL }/user/payCart`, requestOption);
    fetchCart(parameter.id);
    fetchCartHistory(parameter.id);
}

export const deleteFromCart = async (parameter) => {
    const requestOption = {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(parameter)
    };
    await fetch(`${ API_URL }/cart/deleteFromCart`, requestOption);
    fetchCart(parameter.userId);
}

export const deleteAll = async (id) => {
    const requestOption = {
        method: 'DELETE',
        headers: {'Content-Type':'application/json'}
    };
    await fetch(`${ API_URL }/cart/deleteAll/${id}`,requestOption);
    fetchCart(id);
}

export const fetchCartHistory = async (id) => {
    
    const response = await fetch(`${ API_URL }/cart/getCarts/${id}`);
    const data = await response.json();
    
    CartsStore.update(s => { s.carts = data });
}