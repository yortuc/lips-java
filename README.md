## Lips

Lips is a very basic lisp-like, pure functional scripting language, interpreter and repl environment. 


### Recursion example
```
; define a recursive function
(def factorial (x)
    (if (< x 2)
        1
        (mul x (factorial (- x 1)))
    )
)

; print factorial 5
(print (factorial 5))
```
