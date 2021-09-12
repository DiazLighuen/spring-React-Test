import { IonBadge, IonCol, IonRow } from "@ionic/react";
import styles from "./CartProduct.module.scss";
import { CartProduct } from "../components/CartProduct";
import { deleteFromCart, fetchCart } from "./../utils";

export const Purchase = ({ cart, click }) => {
    var date = new Date(Date.parse(cart.purchaseDate));
    var normalDate = date.getDate() + "/"+ (date.getMonth()+1)+ "/" +date.getFullYear();


  return (
    <IonCol size="12" onClick={click}>
      <div className={styles.productContainer}>
        <div className={styles.productInfo}>
          <div>
            <IonBadge color="primary">${cart.total.toFixed(2)}</IonBadge>
          </div>
        </div>
        {/* <h1 className={`${styles.productTitle} truncate`}>{'asd'}</h1> */}
        <h1 className={`${styles.productTitle} truncate`}>{normalDate}</h1>
      </div>
    </IonCol>
  );
};