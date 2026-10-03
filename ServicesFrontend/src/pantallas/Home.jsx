import { useState } from "react";

import Cabecera from "../componentes/Cabecera";
import Cuerpo from "../componentes/Cuerpo.jsx";
import Piedepagina from "../componentes/Piedepagina.jsx";
import Login from "./Login.jsx";
import Signup from "./Signup.jsx";
import Servicios from "./Servicios.jsx";

function Home(){
    const [pantallaActual, setPantallaActual] = useState("cuerpo");
    return(
    <div className="min-h-screen flex flex-col">
        <Cabecera
            dirigirL={() => setPantallaActual("login")}
            dirigirS={() => setPantallaActual("signup")}
            dirigirC={() => setPantallaActual("cuerpo")}
        />
        {
            pantallaActual === "login" &&
            (<Login dirigirS = { () => setPantallaActual("signup") } /> )

        }

        {
            pantallaActual === "signup" &&
            (<Signup dirigirL = { () => setPantallaActual("login") } /> )
        }

        {pantallaActual === "cuerpo" && (<Cuerpo
            dirigirSE={() => setPantallaActual("servicios")}/> )}

        {pantallaActual === "servicios" && (<Servicios />)}
        <Piedepagina />
    </div>
    );
}
export default Home;