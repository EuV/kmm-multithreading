import Foundation
import UIKit
import shared

final class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white

        view.addSubview(container)

        container.axis = .vertical
        container.spacing = 16
        container.distribution = .equalSpacing
        container.alignment = .leading

        buttons.forEach(container.addArrangedSubview(_:))
        buttons.forEach { $0.translatesAutoresizingMaskIntoConstraints = false }

        container.translatesAutoresizingMaskIntoConstraints = false
        container.topAnchor.constraint(equalTo: view.topAnchor, constant: 50).isActive = true
        container.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16).isActive = true
        container.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16).isActive = true
    }

    private let container = UIStackView()

    private func makeButton(title: String) -> UIButton {
        let ret = UIButton(type: .system)
        ret.setTitle(title, for: .normal)
        ret.setAttributedTitle(ViewController.makeAttributedText(from: title), for: .normal)
        ret.contentEdgeInsets = .init(top: 4, left: 6, bottom: 4, right: 6)
        ret.addTarget(self, action: #selector(handleAction(button:)), for: .touchUpInside)
        return ret
    }

    private lazy var buttons: [UIButton] = {
        [
            makeButton(title: "0: Mic Check"),
            makeButton(title: "1: The Problem"),
            makeButton(title: "2: Solution"),
            makeButton(title: "3: Ensure Never Frozen"),
            makeButton(title: "4: Freeze"),
            makeButton(title: "5: Global"),
            makeButton(title: "6: Leaks"),
            makeButton(title: "7: Atomics"),
            makeButton(title: "8: Mutex"),
            makeButton(title: "9: Mutating Via Copying"),
            makeButton(title: "10: Multithreading With DTO"),
            makeButton(title: "11: Pure Mutable"),
            makeButton(title: "12: Dirty Mutable"),
        ]
    }()

    private let problems: [CallWithCallback] = [
        _0_MicCheck(),
        _1_TheProblem(),
        _2_Solution(),
        _3_EnsureNeverFrozen(),
        _4_Freeze(),
        _5_Global(),
        _6_Leaks(),
        _7_Atomics(),
        _8_Mutex(),
        _9_MutatingViaCopying(),
        _10_MultithreadingWithDTO(),
        _11_PureMutable(),
        _12_DirtyMutable()
    ]

    private static func makeAttributedText(from text: String) -> NSAttributedString {
        return NSAttributedString(string: text, attributes: [.font: UIFont.systemFont(ofSize: 20)])
    }

    @objc private func handleAction(button: UIButton) {
        guard let index = buttons.firstIndex(of: button) else { return }
        problems[index].call(callback: { res in
            let newText = (button.attributedTitle(for: .normal)?.string ?? "") + " \(res)"
            button.setAttributedTitle(ViewController.makeAttributedText(from: newText), for: .normal)
        })
    }
}
