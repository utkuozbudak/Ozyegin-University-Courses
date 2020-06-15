class DFS:
    def __init__(self, graph, root):
        self.graph = graph
        self.visited = dict()
        self.stack = list()
        self.stack.append(root)
        self.counter = 0

    def run(self, target):

        while self.stack:

            current_node = self.stack.pop()
            self.counter += 1
            if current_node.is_equal(target):
                return True, self.counter, current_node.step
            self.visited[current_node.UID] = current_node

            for n in self.graph.reveal_neighbors(current_node):
                if n.UID not in self.visited:
                    self.stack.append(n)

        return False, 0, 0
