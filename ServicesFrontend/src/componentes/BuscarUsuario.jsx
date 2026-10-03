const API = import.meta.env.VITE_API_URL || "/api";

import { useState } from "react";

function BuscarUsuario(){
    const[usuarios,setUsuarios]= useState([]);
    const[cargando,setCargando]= useState(false);

    function buscarUsuario() {
        setCargando(true);
        fetch(`${API}/admin/usuarios`)
            .then((respuesta) => respuesta.json())
            .then((datos) => {
                setUsuarios(datos);
                setCargando(false);
            })
            .catch((error) => {
                console.error("Error al cargar usuarios:", error);
                setCargando(false);
            });
    }
    return (
       <div className="p-6">
            <button 
                onClick={buscarUsuario} 
                className="bg-blue-600 text-white font-bold px-4 py-2 rounded shadow hover:bg-blue-700 transition"
            >
                Lista de Usuarios
            </button>

            {cargando && <p className="mt-4 text-gray-600">Cargando...</p>}
            
            {cargando && usuarios.length === 0 && (
                <p className="mt-4 text-gray-500">Aún no hay resultados.</p>
            )}

            {usuarios.length > 0 && (
                <div className="overflow-x-auto mt-6">
                    <table className="min-w-full bg-white border border-gray-200 shadow-md rounded-lg overflow-hidden">
                        <thead className="bg-gray-100 text-gray-700 uppercase text-xs leading-normal">
                            <tr className="border-b border-gray-200">
                                <th className="py-3 px-6 text-left">ID</th>
                                <th className="py-3 px-6 text-left">Nombres</th>
                                <th className="py-3 px-6 text-left">Apellidos</th>
                                <th className="py-3 px-6 text-left">DNI</th>
                                <th className="py-3 px-6 text-left">Correo</th>
                                <th className="py-3 px-6 text-left">Teléfono</th>
                                <th className="py-3 px-6 text-center">Estado</th>
                                <th className="py-3 px-6 text-left">Fecha de Creacion</th>
                                <th className="py-3 px-6 text-left">Frecha de Actualizacion</th>
                            </tr>
                        </thead>
                        <tbody className="text-gray-600 text-sm font-light">
                            {usuarios.map((usuario) => (
                                <tr key={usuario.idUsuario} className="border-b border-gray-200 hover:bg-gray-50 transition">
                                    <td className="py-3 px-6 text-left whitespace-nowrap">{usuario.idUsuario}</td>
                                    <td className="py-3 px-6 text-left">{usuario.nombres}</td>
                                    <td className="py-3 px-6 text-left">{usuario.apellidos}</td>
                                    <td className="py-3 px-6 text-left">{usuario.dni}</td>
                                    <td className="py-3 px-6 text-left">{usuario.email}</td>
                                    <td className="py-3 px-6 text-left">{usuario.telefono}</td>
                                    <td className="py-3 px-6 text-center">
                                        <span className={`px-2.5 py-1 rounded-full text-xs font-semibold ${usuario.activo !== false ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'}`}>
                                            {usuario.activo !== false ? 'Activo' : 'Inactivo'}
                                        </span>
                                    </td>
                                    <td className="py-3 px-6 text-left">{usuario.createdAt}</td>
                                    <td className="py-3 px-6 text-left">{usuario.updatedAt}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
} export default BuscarUsuario;
