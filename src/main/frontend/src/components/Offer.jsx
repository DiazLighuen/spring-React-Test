import { IonCol, IonRow } from "@ionic/react";
import styles from "./Offer.module.scss";
import { useStoreState } from "pullstate";
import { SpecialMonthStore } from "../store";
import { getSpecialMonth } from "../store/Selectors";

export const Offer = ({ specialMonth, image, heading }) => {

  const monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
  "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  const date = useStoreState(SpecialMonthStore, getSpecialMonth);
  const month = new Date(Date.parse(date.date)).getUTCDate()-1;

  return (
	<IonRow>
    <IonCol size="12">
      <div className={ styles.offer }>
        <div>
          <h1>{ heading }</h1>
          <p>{ monthNames[month] }</p>
        </div>
        <img src={ image } alt="offer" />
      </div>
    </IonCol>
  </IonRow>
  )};