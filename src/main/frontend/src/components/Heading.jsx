import { IonButton, IonCol, IonRow } from "@ionic/react";
import styles from "./Heading.module.scss";

export const Heading = ({ heading, buttonClick = false, buttonText }) => (

  <IonRow>
    <IonCol size="12" className={ styles.heading }>
      <h5>{ heading }</h5>
      { buttonClick && <IonButton fill="solid" onClick={ buttonClick }>{ buttonText }</IonButton> }
    </IonCol>
  </IonRow>
);