import {
  IonBadge,
  IonButton,
  IonButtons,
  IonCard,
  IonCardContent,
  IonCol,
  IonContent,
  IonGrid,
  IonHeader,
  IonIcon,
  IonPage,
  IonRow,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import { cart, star } from "ionicons/icons";
import { useRef } from "react";
import { addToCart } from "./../utils";
import { CartProduct } from "../components/CartProduct";


import styles from "./ProductModal.module.scss";

export const CartModal = ({ dismiss, cart }) => {
  const cartRef = useRef();
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Purchase</IonTitle>

          <IonButtons slot="end">
            <IonButton onClick={dismiss}>Close</IonButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonGrid>
          <IonRow className="animate__animated animate__faster animate__slideInUp">
            <IonCol size="12">
              <IonRow>
                {cart.products.map((product, index) => {
                  return (
                    <CartProduct
                      product={product}
                      key={index}
                    />
                  );
                })}
              </IonRow>
            </IonCol>
          </IonRow>
        </IonGrid>

        <IonGrid
          className={`${styles.bottom} animate__animated animate__slideInUp`}
        ></IonGrid>
      </IonContent>
    </IonPage>
  );
};
