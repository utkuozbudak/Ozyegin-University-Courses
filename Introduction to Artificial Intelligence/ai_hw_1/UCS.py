import queue as Q


class UCS:
    def __init__(self, graph, root):
        self.graph = graph
        self.visited = dict()
        self.queue = Q.PriorityQueue()
        self.counter = 0
        self.visited[root.UID] = root
        self.queue.put((root.step, root, int(root.UID)))

    def run(self, target):

        while self.queue:

            current_node = self.queue.get()[1]
            self.counter += 1
            if current_node.is_equal(target):
                return True, self.counter, current_node.step
            self.visited[current_node.UID] = current_node

            for n in self.graph.reveal_neighbors(current_node):
                if n.UID not in self.visited:
                    self.queue.put((n.step, n, int(n.UID)))

        return False, 0, 0
