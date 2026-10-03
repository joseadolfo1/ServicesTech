function Catalogo({ volverAlLogin }) {
    return (
        <div>
            <button type="text"
                    className="bg-white border border-gray-400"  onClick={volverAlLogin}>
                ⬅️ Cerrar sesión
            </button>
            <h1>Catálogo</h1>
        </div>
    );
}
export default Catalogo;