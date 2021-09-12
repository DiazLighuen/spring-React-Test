import { createSelector } from 'reselect';

const getState = state => state;

//  General getters
export const getCart = createSelector(getState, state => state.cart);
export const getCarts = createSelector(getState, state => state.carts);
export const getProducts = createSelector(getState, state => state.products);
export const getSpecialMonth = createSelector(getState, state => state.specialMonth);

//  Specific getters
export const getProduct = id => createSelector(getState, state => state.products.filter(p => parseInt(p.id) === parseInt(id))[0]);