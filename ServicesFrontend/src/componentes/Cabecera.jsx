function Cabecera({dirigirL,dirigirS,dirigirC}){
    return(
        <div className="mx-auto p-1 flex items-center justify-center text-black space-x-20 w-full border-b-1 border-gray-200">
            <a onClick={dirigirC} ><img src="../assets/logoST.svg" className="py-1 h-18 " /></a>
            <ul className="flex space-x-2 flex items-center justify-between text-sm">
                <li className="relative">
                    <button onClick={() => {
                        document.querySelector("#opcion2").classList.add("hidden");
                        document.querySelector("#opcion1").classList.toggle("hidden");
                    }}
                            className="bg-sky-50 text-orange-800 font-bold px-3 py-2 rounded-xl border border-orange-200 hover:cursor-pointer">Portal Cliente</button>
                    <div id="opcion1" className="hidden absolute top-full left-0 mt-2 w-48 bg-white border border-gray-200 rounded-xl shadow-lg overflow-hidden z-50">
                        <a onClick={dirigirL} className="block px-4 py-3 text-gray-700 hover:bg-sky-50 hover:text-sky-600 hover:cursor-pointer">Iniciar sesión</a>
                        <a onClick={dirigirS} className="block px-4 py-3 text-gray-700 hover:bg-orange-50 hover:text-orange-500 hover:cursor-pointer">Crear una cuenta</a>
                    </div>
                </li>
                <li className="relative">
                    <button onClick={() => {
                        document.querySelector("#opcion1").classList.add("hidden");
                        document.querySelector("#opcion2").classList.toggle("hidden");
                    }}
                            className="bg-black text-white px-3 py-2 font-bold rounded-xl border-1 hover:cursor-pointer"><a>Acceso Personal</a></button>
                    <div id="opcion2" className="hidden absolute top-full left-0 mt-2 w-48 bg-white border border-gray-200 rounded-xl shadow-lg overflow-hidden z-50">
                        <a  onClick={dirigirL} className="block px-4 py-3 text-gray-700 hover:bg-sky-50 hover:text-sky-600 hover:cursor-pointer">Iniciar sesión</a>
                    </div>
                </li>
            </ul>
            <ul className="flex space-x-10 flex items-center " >
                <li className="hover:text-gray-200 hover:cursor-pointer"><a onClick={dirigirC}>Inicio</a></li>
                <li className="hover:text-gray-200"><a>Servicios</a></li>
                <li className="hover:text-gray-200"><a>Nosotros</a></li>
                <li className="hover:text-gray-200"><a>Casos</a></li>
                <li className="hover:text-gray-200"><a>Blog</a></li>
                <li className="hover:text-gray-200"><a>Contacto</a></li>
                <li><button className="bg-white text-green-900 px-4 py-2 font-bold rounded-xl border-2 border-green-500" ><a href="https://api.whatsapp.com/send/?phone=51928536863&text=Hola+ServicesTech%2C+quisiera+informaci%C3%B3n+sobre+soporte+t%C3%A9cnico&type=phone_number&app_absent=0">WhatsApp</a></button></li>
            </ul>
        </div>
    );
}
export default Cabecera;