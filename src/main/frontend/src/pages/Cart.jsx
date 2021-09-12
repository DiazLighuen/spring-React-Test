import {
  IonButton,
  IonCol,
  IonContent,
  IonGrid,
  IonHeader,
  IonPage,
  IonRow,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import { useStoreState } from "pullstate";
import { CartProduct } from "../components/CartProduct";
import { Heading } from "../components/Heading";
import { CartStore } from "../store";
import { CartsStore } from "../store";
import { getCart } from "../store/Selectors";
import { fetchCart } from "./../utils";
import { useEffect } from "react";
import { payCart, deleteAll, fetchCartHistory } from "./../utils";


import styles from "./Cart.module.scss";

const Cart = () => {

  const cart = useStoreState(CartStore, getCart);

  useEffect(() => {
    fetchCart(1);
  }, []);

  const handlePayCart = () => {
    payCart({
      "id":"1",
      "username":"lighuen"
    });
  };

  const handleDeleteAll = () => {
    deleteAll(1);
  };

  const handleHistory = () => {
    fetchCartHistory(1);
  };

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Cart</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Cart</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonGrid>
          {cart && cart.products && cart.products.length < 1 && (
            <IonRow className={styles.emptyCartContainer}>
              <IonCol size="10" className="ion-text-center">
                <div className={styles.text}>
                  <img src="/assets/cart.png" alt="no cart" />
                  <h1>Hang on there!</h1>
                  <p>Your cart is empty</p>
                  <IonButton color="primary" routerLink="/home">
                    Shop now &rarr;
                  </IonButton>
                </div>
              </IonCol>
            </IonRow>
          )}

          {cart && cart.products && cart.products.length > 0 && (
            <>
              <IonRow className={styles.cartContainer}>
                <IonCol size="12">
                  <Heading
                    heading={`You have ${cart.products.length} products in your cart. Your total comes to $${cart.total}.`}
                    buttonClick= {handleDeleteAll}
                    buttonText={`Empty cart`}
                  />
                  {/* <IonButton onClick={handleDeleteAll}>Empty cart</IonButton> */}
                </IonCol>
              </IonRow>

              <IonRow>
                {cart.products.map((product, index) => {
                  return <CartProduct product={product} key={index} canDelete={true} />;
                })}
              </IonRow>
            </>
          )}
        </IonGrid>

        {cart && cart.products && cart.products.length > 0 && (
          <IonGrid className={styles.bottom}>
            <IonRow>
              <IonCol size="12">
                <IonButton onClick={handlePayCart} color="primary" expand="block">
                  Checkout (${cart.total})
                </IonButton>
              </IonCol>
            </IonRow>
          </IonGrid>
        )}
      </IonContent>
    </IonPage>
  );
};

export default Cart;
