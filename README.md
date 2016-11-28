# ikvm-socket-error
proof of concept for socket error

This example runs perfectly fine with java - it is basically the rabbitmq example for send/recv

Once you start this via ikvm exe, you get timeouts after 15 seconds on the sender side, because a read timeout hits on the used socket... which is fine, because it only sends stuff. But this exception isn't occuring while running via java.
