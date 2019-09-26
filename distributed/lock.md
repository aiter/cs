### redis
> setnx    SETNX lock.foo <current Unix time + lock timeout + 1>
> get
> getset  GETSET lock.foo <current Unix timestamp + lock timeout + 1>
> 
> unlock

> In order to make this locking algorithm more robust, a client holding a lock should always check the timeout didn't expire before unlocking the key with DEL because client failures can be complex, not just crashing but also blocking a lot of time against some operations and trying to issue DEL after a lot of time (when the LOCK is already held by another client).
