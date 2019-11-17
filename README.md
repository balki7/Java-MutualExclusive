# Java-MutualExclusive

Processes A and B want to access file X first and then Y.
All accesses are mutually exclusive. The requirement is that whichever of A and B accesses X first must also be first to access Y.
For example, if X is accessed in the order BA, then Y must also be accessed in the order BA.
