class BFS:
    def __init__(self, graph, root):
        self.graph = graph
        self.visited = dict()
        self.queue = list()
        self.counter = 0
        self.visited[root.UID] = root
        self.queue.append(root)

    def run(self, target):

        r = self.queue.pop()
        self.counter += 1
        if r.is_equal(target):
            return True, self.counter, r.step

        self.queue.append(r)

        while self.queue:
            current_node = self.queue.pop(0)
            self.visited[current_node.UID] = current_node

            for n in self.graph.reveal_neighbors(current_node):
                if n.UID not in self.visited:
                    self.counter += 1
                    if n.is_equal(target):
                        return True, self.counter, n.step
                    self.queue.append(n)

        return False, 0, 0




