import { IonBadge, IonCol, IonIcon, IonNote } from "@ionic/react";
import { star } from "ionicons/icons";
import styles from "./Product.module.scss";

export const Product = ({ product, click, fromHome = false }) => {

  return (

    <IonCol size="6" onClick={ click }>
      <div className={ styles.productContainer }>
        {/* <div style={{ backgroundImage: `url(${ product.image })` }} className={ styles.coverImage } /> */}
        <h1 className={ `${ styles.productTitle } truncate` }>{ product.name }</h1>

        <div className={ styles.productInfo }>
          <div>
            <IonBadge color="primary">${ product.price.toFixed(2) }</IonBadge>
          </div>
        </div>
      </div>
    </IonCol>
  );
}