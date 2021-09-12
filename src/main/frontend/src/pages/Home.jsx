import { IonContent, IonGrid, IonHeader, IonModal, IonPage, IonTitle, IonToolbar, useIonModal } from '@ionic/react';
// import axios from 'axios';
import { useRef } from 'react';
import { useState, useEffect } from 'react';
import { Heading } from '../components/Heading';
import { Offer } from '../components/Offer';
import { ProductModal } from '../components/ProductModal';
import { Products } from '../components/Products';

const Home = () => {

  const pageRef = useRef();
  const [ showModal, setShowModal ] = useState(false);
  const [ selectedProduct, setSelectedProduct ] = useState(false);

  const handleShowModal = product => {

    setSelectedProduct(product);
    present({
      
      cssClass: "product-modal",
      presentingElement: pageRef.current
    });
  }
  

  const [ present, dismiss ] = useIonModal(ProductModal, {

    dismiss: () => dismiss(),
    product: selectedProduct
  });

  return (
    <IonPage ref={ pageRef }>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Diaz Lighuen test</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Diaz Lighuen test</IonTitle>
          </IonToolbar>
        </IonHeader>

        <IonGrid>
          <Offer image="/assets/shop.png" heading="Mes de rebajas!" />
          <Heading heading="Productos" />
          <Products click={ handleShowModal } />
        </IonGrid>
      </IonContent>

      <IonModal isOpen={ showModal } onDidDismiss={ () => setShowModal(false) } breakpoints={ [0, 0.27, 0.5, 1] } initialBreakpoint={ 0.27 } backdropBreakpoint={ 0.5 }>
      </IonModal>
    </IonPage>
  );
};

export default Home;
