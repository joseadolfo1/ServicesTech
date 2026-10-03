function Login(dirigirS){
    return (
        <main className="flex min-h-[calc(100vh-200px)] items-center justify-center py-12 px-4">
            <div className="w-full max-w-md space-y-6">

                <h1 className="text-center text-2xl font-bold text-gray-900">Inicia sesión en tu cuenta</h1>

                <form className="space-y-4">
                    <div className="flex flex-col gap-1.5">
                        <label htmlFor="email" className="text-sm font-medium text-gray-700 after:content-['*'] after:ml-0.5 after:text-red-500">Correo Electronico</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="ejemplo@tudominio.com"
                            className="w-full rounded-lg border border-gray-300 px-3.5 py-2.5 text-gray-900 shadow-xs focus:border-indigo-500 focus:outline-hidden focus:ring-1 focus:ring-indigo-500
                    invalid:border-red-500 invalid:text-red-600
                    focus:invalid:border-red-500 focus:invalid:ring-red-500 "
                            required
                        />
                    </div>

                    <div className="flex flex-col gap-1.5">
                        <label htmlFor="password" className="text-sm font-medium text-gray-700 after:content-['*'] after:ml-0.5 after:text-red-500">Contraseña</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            placeholder="••••••••"
                            className="w-full rounded-lg border border-gray-300 px-3.5 py-2.5 text-gray-900 shadow-xs focus:border-indigo-500 focus:outline-hidden focus:ring-1 focus:ring-indigo-500 focus:invalid:border-red-500 focus:invalid:ring-red-500
                    invalid:border-red-500 invalid:text-red-600"
                            required
                            minLength={9}
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full rounded-lg bg-sky-600 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-sky-500 active:bg-indigo-700 transition-colors cursor-pointer"
                    >
                        Iniciar sesión
                    </button>
                </form>
            </div>
        </main>
    );
}
export default Login;