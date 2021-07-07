package com.example.newapp.lib.core.coroutines;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\f\u001a\u00020\u00048VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\r\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/example/newapp/lib/core/coroutines/CoroutineDispatchersProvider;", "Lcom/example/newapp/lib/core/coroutines/CoroutineDispatchers;", "()V", "default", "Lkotlin/coroutines/CoroutineContext;", "getDefault", "()Lkotlin/coroutines/CoroutineContext;", "default$delegate", "Lkotlin/Lazy;", "io", "getIo", "io$delegate", "main", "getMain", "main$delegate", "lib_core_debug"})
public final class CoroutineDispatchersProvider implements com.example.newapp.lib.core.coroutines.CoroutineDispatchers {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy main$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy io$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy default$delegate = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getMain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getIo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getDefault() {
        return null;
    }
    
    @javax.inject.Inject()
    public CoroutineDispatchersProvider() {
        super();
    }
}