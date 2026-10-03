export default function Cuerpo({dirigirSE}) {
    return (
        <main className="flex-1 min-h-0 overflow-hidden">
        <div className="relative w-full h-full">
            <img src="../img/chan.png" className=" w-full h-full object-cover object-center" />
            <div className="absolute inset-0 flex items-center">
                <div className="ml-10 md:ml-20 max-w-xl">
                    <h1 className="text-sky-500 font-bold text-4xl md:text-6xl font-bold drop-shadow-lg">
                        Soluciones tecnológicas
                    </h1>
                    <p className="text-orange-500 py-4 text-lg font-bold md:text-2xl mt-4 drop-shadow-md">
                        Servicio técnico profesional para tus equipos.
                    </p>
                    <button onClick={dirigirSE} className="mt-14 ml-40 bg-sky-400 hover:bg-sky-500 transition-colors hover:cursor-pointer text-white border-2 border-sky-950 rounded-xl p-2">Solicitar Servicio</button>
                </div>
            </div>
        </div>
            </main>
    );
}
