import { useState } from "react";
import BuscarUsuario from "../componentes/BuscarUsuario.jsx";

function Servicios(){
    return(
    <div>
        <h1 className="font-bold text-center p-6 " >API de los Guerreros Z</h1>
        <BuscarUsuario />
    </div>
    );
} export default Servicios;