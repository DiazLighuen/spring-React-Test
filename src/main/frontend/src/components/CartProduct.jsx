import { IonBadge, IonCol, IonRow, IonButton } from "@ionic/react";
import styles from "./Product.module.scss";
import { deleteFromCart } from "./../utils";

export const CartProduct = ({ product, click, fromHome = false, canDelete=false }) => {

  const handleDelete = () => {
    deleteFromCart({
      productId: product.id,
      userId: 1,
    });
  };

  return (
    <IonCol
      size="12"
      onClick={click}
      className={
        !fromHome
          ? "animate__animated animate__faster animate__slideInRight"
          : null
      }
    >
      <div className={styles.productContainer}>
        <div className={styles.productInfo}>
          <div>
            <IonBadge color="primary">${product.price.toFixed(2)}</IonBadge>
          </div>
        </div>
        <h1 className={`${styles.productTitle} truncate`}>{product.name}</h1>
        {/* <div style={{ backgroundImage: `url(${ product.image })` }} className={ styles.coverImage } /> */}
        { canDelete 
          ? <IonButton onClick={handleDelete}>Delete</IonButton> 
          : null
        }
      </div>

    </IonCol>
  );
}