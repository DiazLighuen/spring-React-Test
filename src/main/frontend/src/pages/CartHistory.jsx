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
  useIonModal,
  IonModal
} from "@ionic/react";
import { useStoreState } from "pullstate";
import { CartsStore } from "../store";
import { getCarts } from "../store/Selectors";
import { useState, useRef} from "react";
import { CartModal } from '../components/CartModal';

import styles from "./Cart.module.scss";
import { Purchase } from "../components/Purchase";

const CartHistory = () => {
  const carts = useStoreState(CartsStore, getCarts);

  const pageRef = useRef();
  const [ showModal, setShowModal ] = useState(false);
  const [ selectedCart, setSelectedCart ] = useState(false);

  const handleShowModal = cart => {

    setSelectedCart(cart);
    present({
      
      cssClass: "product-modal",
      presentingElement: pageRef.current
    });
  }
  

  const [ present, dismiss ] = useIonModal(CartModal, {

    dismiss: () => dismiss(),
    cart: selectedCart
  });

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Cart history</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Cart history</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonGrid>
          {carts && carts.length < 1 && (
            <IonRow className={styles.emptyCartContainer}>
              <IonCol size="10" className="ion-text-center">
                <div className={styles.text}>
                  <img src="/assets/cart.png" alt="no cart" />
                  <h1>You have no purchase history yet</h1>
                  <IonButton color="primary" routerLink="/home">
                    Shop now &rarr;
                  </IonButton>
                </div>
              </IonCol>
            </IonRow>
          )}

          {carts && carts.length > 0 && (
            <>
              <IonRow>
                {carts.map((cart) => {
                  return <Purchase cart={cart} click={ () => handleShowModal(cart) } />;
                })}
              </IonRow>
            </>
          )}
        </IonGrid>
      </IonContent>
      <IonModal isOpen={ showModal } onDidDismiss={ () => setShowModal(false) } breakpoints={ [0, 0.27, 0.5, 1] } initialBreakpoint={ 0.27 } backdropBreakpoint={ 0.5 }>
      </IonModal>
    </IonPage>
  );
};

export default CartHistory;
